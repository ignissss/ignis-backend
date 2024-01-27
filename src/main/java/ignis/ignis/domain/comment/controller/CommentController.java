package ignis.ignis.domain.comment.controller;

import ignis.ignis.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "comment", description = "댓글")
@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @Operation(description = "댓글달기")
    @PostMapping("/{feedId}")
    public void createComment(@PathVariable Long feedId, String comment){
        commentService.create(feedId, comment);
    }
}
