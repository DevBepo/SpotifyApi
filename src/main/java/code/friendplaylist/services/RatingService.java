package code.friendplaylist.services;

import code.friendplaylist.domain.Rating;
import code.friendplaylist.domain.User;
import code.friendplaylist.dto.RatingDto;
import code.friendplaylist.repository.RatingRepository;
import code.friendplaylist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveRating(String playlistId, RatingDto ratingDto, String userNickname) {
        User user = userRepository.findByNickname(userNickname)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<Rating> existingRating = ratingRepository.findByUserIdAndPlaylistId(user.getId(), playlistId);

        Rating rating = existingRating.orElse(new Rating(user, playlistId, ratingDto.getRating()));
        if (existingRating.isPresent()) {
            rating.setScore(ratingDto.getRating());
        }

        ratingRepository.save(rating);
    }

    @Transactional(readOnly = true)
    public Optional<Integer> getRating(String playlistId, String userNickname) {
        User user = userRepository.findByNickname(userNickname)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return ratingRepository.findByUserIdAndPlaylistId(user.getId(), playlistId)
                .map(Rating::getScore);
    }
}
