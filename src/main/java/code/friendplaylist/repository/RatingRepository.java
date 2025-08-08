package code.friendplaylist.repository;

import code.friendplaylist.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndPlaylistId(Long userId, String playlistId);
}
