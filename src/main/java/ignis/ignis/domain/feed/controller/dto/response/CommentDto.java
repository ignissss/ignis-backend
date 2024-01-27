package ignis.ignis.domain.feed.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long authorId;
    private String authorName;
    private String authorProfileUrl;
    private String content;
    private LocalDateTime createdDate;
}
