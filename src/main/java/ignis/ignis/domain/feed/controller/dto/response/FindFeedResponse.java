package ignis.ignis.domain.feed.controller.dto.response;

import ignis.ignis.domain.comment.domain.Comment;
import ignis.ignis.domain.feed.domain.Feed;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class FindFeedResponse {
    private Long id;
    private String title;
    private String imageUrl;
    private String user;
    private LocalDateTime createAt;
    private Integer count;
    private List<Comment> comments;

    public  FindFeedResponse (Feed feed) {
        this.title = feed.getTitle();
        this.id = feed.getId();
        this.imageUrl = feed.getImageUrl();
        this.user = feed.getUser().getUserName();
        this.createAt = feed.getCreateAt();
        this.count = feed.getCount();
        this.comments = feed.getComments();
    }
}