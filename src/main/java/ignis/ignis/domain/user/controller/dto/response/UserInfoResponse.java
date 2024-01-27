package ignis.ignis.domain.user.controller.dto.response;

import ignis.ignis.domain.feed.domain.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponse {
    private Long id;
    private String userName;
    private Integer age;
    private String profileUrl;
    private Integer point;
    private List<Feed> feeds;
}
