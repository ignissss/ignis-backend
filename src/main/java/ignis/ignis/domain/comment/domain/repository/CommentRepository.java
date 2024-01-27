package ignis.ignis.domain.comment.domain.repository;

import ignis.ignis.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
