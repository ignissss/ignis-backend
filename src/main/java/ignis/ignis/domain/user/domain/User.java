package ignis.ignis.domain.user.domain;

import ignis.ignis.domain.user.controller.dto.request.SignupRequest;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private Integer re;

    public void addRe() {
        this.re = this.re + 5;
    }

    public void signup(SignupRequest request) {
        this.userName = request.getUserName();
        this.age = request.getAge();
    }
}
