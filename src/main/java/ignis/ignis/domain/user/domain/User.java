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

    @Column(nullable = false)
    private Integer re;

    @OneToMany(mappedBy = "user")
    private List<Feed> feed = new ArrayList<>();

    public void addRe() {
        this.re = this.re + 5;
    }

    public void signup(SignupRequest request) {
        this.userName = request.getUserName();
        this.age = request.getAge();
    }
}
