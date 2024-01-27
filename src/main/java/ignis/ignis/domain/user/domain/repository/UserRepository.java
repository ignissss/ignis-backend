package ignis.ignis.domain.user.domain.repository;

import ignis.ignis.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
     Optional<User> findByUserName(String userName);

     boolean existsByToken(String token);
}
