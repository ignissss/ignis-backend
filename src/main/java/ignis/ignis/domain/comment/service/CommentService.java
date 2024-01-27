package ignis.ignis.domain.comment.service;

import ignis.ignis.domain.comment.domain.Comment;
import ignis.ignis.domain.comment.domain.repository.CommentRepository;
import ignis.ignis.domain.feed.domain.Feed;
import ignis.ignis.domain.feed.domain.repository.FeedRepository;
import ignis.ignis.domain.user.domain.User;
import ignis.ignis.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserFacade userFacade;
    private final FeedRepository feedRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void create(Long feedId, String comment){
        Feed feed = feedRepository.findById(feedId).orElseThrow(()-> new RuntimeException("null"));
        User user = userFacade.getCurrentUser();
        LocalDateTime createAt = LocalDateTime.now();

       commentRepository.save(
                Comment.builder()
                        .comment(comment)
                        .user(user)
                        .feed(feed)
                        .createdDate(createAt)
                        .build());
    }
}
