package ignis.ignis.domain.feed.domain;

import ignis.ignis.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountRepository extends JpaRepository<Count, Long> {
    Boolean existsByUserAndFeed(User user, Feed feed);

    void deleteByUserAndFeed(User user, Feed feed);
}
