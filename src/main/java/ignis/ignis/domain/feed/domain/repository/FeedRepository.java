package ignis.ignis.domain.feed.domain.repository;

import ignis.ignis.domain.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findByTitle(String title);
}
