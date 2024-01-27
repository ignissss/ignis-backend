package ignis.ignis.domain.feed.domain;

import ignis.ignis.domain.comment.domain.Comment;
import ignis.ignis.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String imageUrl;

    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer count;

    @OneToMany(mappedBy = "feed")
    private List<Comment> comments;

    public void addCount() {
        this.count += 1;
    }
    public void deleteCount() {
        this.count -=1;
    }
}
