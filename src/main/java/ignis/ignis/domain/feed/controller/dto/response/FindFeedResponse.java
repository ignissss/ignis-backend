package ignis.ignis.domain.feed.controller.dto.response;

import ignis.ignis.domain.comment.domain.Comment;
import ignis.ignis.domain.feed.domain.Feed;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FindFeedResponse {
    private Long id;
    private String title;
    private String imageUrl;
    private String user;
    private LocalDateTime createAt;
    private Integer count;
    private boolean isLiked;
    private List<CommentDto> comments;

    public  FindFeedResponse (Feed feed, boolean isLiked) {
        this.title = feed.getTitle();
        this.id = feed.getId();
        this.imageUrl = feed.getImageUrl();
        this.user = feed.getUser().getUserName();
        this.createAt = feed.getCreateAt();
        this.count = feed.getCount();
        this.isLiked = isLiked;
        this.comments = feed.getComments().stream().map(
                comment -> CommentDto.builder()
                        .authorId(comment.getUser().getId())
                        .authorName(comment.getUser().getUserName())
                        .authorProfileUrl(comment.getUser().getProfileUrl())
                        .content(comment.getComment())
                        .createdDate(comment.getCreatedDate())
                        .build()
        ).collect(Collectors.toList());
    }
}
