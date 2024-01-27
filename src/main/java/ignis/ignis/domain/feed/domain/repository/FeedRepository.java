package ignis.ignis.domain.feed.domain.repository;

import ignis.ignis.domain.feed.domain.Feed;
import ignis.ignis.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findByTitleContains(String title);

    @Query(value = "SELECT * FROM feed " +
            "WHERE (6371 * acos(cos(radians(:userLatitude)) * cos(radians(x)) * cos(radians(y) - radians(:userLongitude)) + sin(radians(:userLatitude)) * sin(radians(x)))) <= 1.0",
            nativeQuery = true)
    List<Feed> findFeedsNearUser(@Param("userLatitude") double userLatitude,
                                 @Param("userLongitude") double userLongitude);


    boolean existsByIdAndUser(Long feedId,User user);
}

