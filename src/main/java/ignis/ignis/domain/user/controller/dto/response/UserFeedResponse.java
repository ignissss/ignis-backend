package ignis.ignis.domain.user.controller.dto.response;

import ignis.ignis.domain.feed.domain.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFeedResponse {
    private Long id;
    private String title;
    private String imageUrl;
    private String authorName;
    private LocalDateTime createdDate;
    private Integer likes;

    public UserFeedResponse(Feed feed) {
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.imageUrl = feed.getImageUrl();
        this.authorName = feed.getUser().getUserName();
        this.createdDate = feed.getCreateAt();
        this.likes = feed.getCount();
    }
}
