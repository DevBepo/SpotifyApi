package code.friendplaylist.services;

import code.friendplaylist.domain.Rating;
import code.friendplaylist.domain.User;
import code.friendplaylist.dto.AverageRatingDto;
import code.friendplaylist.dto.RatingDto;
import code.friendplaylist.repository.RatingRepository;
import code.friendplaylist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveRating(String playlistId, RatingDto ratingDto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));

        Optional<Rating> existingRating = ratingRepository.findByUser_IdAndPlaylistId(user.getId(), playlistId);

        Rating rating = existingRating.orElse(new Rating(user, playlistId, ratingDto.getRating()));
        if (existingRating.isPresent()) {
            rating.setScore(ratingDto.getRating());
        }

        ratingRepository.save(rating);
    }

    @Transactional(readOnly = true)
    public Optional<Integer> getRating(String playlistId, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));

        return ratingRepository.findByUser_IdAndPlaylistId(user.getId(), playlistId)
                .map(Rating::getScore);
    }

    @Transactional(readOnly = true)
    public AverageRatingDto getAverageRating(String playlistId) {
        List<Rating> ratings = ratingRepository.findByPlaylistId(playlistId);
        
        if (ratings.isEmpty()) {
            return new AverageRatingDto(0.0, 0);
        }
        
        double average = ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);
        
        return new AverageRatingDto(average, ratings.size());
    }
}