package code.friendplaylist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.friendplaylist.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByDisplayNameContainingIgnoreCase(String query);

    Optional<User> findByNickname(String nickname);
}
