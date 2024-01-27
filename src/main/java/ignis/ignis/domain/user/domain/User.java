package ignis.ignis.domain.user.domain;

import ignis.ignis.domain.feed.domain.Feed;
import ignis.ignis.domain.user.controller.dto.request.SignupRequest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String userName;

    private Integer age;

    private String profileUrl;

    private Integer rewards;

    @OneToMany(mappedBy = "user")
    private List<Feed> feed = new ArrayList<>();

    public void addRe() {
        this.rewards = (this.rewards != null) ? this.rewards + 5 : 5;
    }

    public void signup(SignupRequest request) {
        this.userName = request.getUserName();
        this.age = request.getAge();
        this.rewards = 0;
    }

    public void initRe() {
        this.rewards = 0;
    }
}
