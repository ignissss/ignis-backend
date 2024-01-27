package ignis.ignis.domain.feed.controller.dto.response;

import ignis.ignis.domain.feed.domain.Feed;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FindAllFeedResponse {
    private Long id;
    private String title;
    private String imageUrl;
    private String user;
    private LocalDateTime createAt;
    private Integer count;

    public static FindAllFeedResponse findAllFeedResponse(Feed feed) {
        return FindAllFeedResponse.builder()
                .id(feed.getId())
                .title(feed.getTitle())
                .createAt(feed.getCreateAt())
                .imageUrl(feed.getImageUrl())
                .user(feed.getUser().getUserName())
                .count(feed.getCount())
                .build();
    }
}
