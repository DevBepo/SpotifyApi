package code.friendplaylist.repository;

import code.friendplaylist.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPlaylistIdOrderByCreatedAtDesc(String playlistId);
}