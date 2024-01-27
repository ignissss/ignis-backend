package ignis.ignis.domain.comment.domain;

import ignis.ignis.domain.feed.domain.Feed;
import ignis.ignis.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;
}
