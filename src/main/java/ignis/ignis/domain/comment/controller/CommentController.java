package ignis.ignis.domain.comment.controller;

import ignis.ignis.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{feedId}")
    public void createComment(@PathVariable Long feedId, String comment){
        commentService.create(feedId, comment);
    }
}
