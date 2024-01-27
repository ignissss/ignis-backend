package ignis.ignis.domain.user.controller.dto.response;

import ignis.ignis.domain.feed.domain.Feed;
import ignis.ignis.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String userName;
    private Integer age;
    private String profileUrl;
    private Integer point;
    private List<Feed> feeds;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.age = user.getAge();
        this.profileUrl = user.getProfileUrl();
        this.point = user.getRe();
        this.feeds = user.getFeed();
    }
}
