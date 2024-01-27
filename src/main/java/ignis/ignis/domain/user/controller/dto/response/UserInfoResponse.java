package ignis.ignis.domain.user.controller.dto.response;

import ignis.ignis.domain.feed.domain.Feed;
import ignis.ignis.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String userName;
    private String email;
    private Integer age;
    private String profileUrl;
    private Integer point;
    private List<UserFeedResponse> feeds;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.profileUrl = user.getProfileUrl();
        this.point = user.getRe();
        this.feeds = user.getFeed().stream().map(UserFeedResponse::new).collect(Collectors.toList());
    }
}
