package ignis.ignis.domain.feed.controller;

import ignis.ignis.domain.feed.controller.dto.request.XYRequest;
import ignis.ignis.domain.feed.controller.dto.response.CountResponse;
import ignis.ignis.domain.feed.controller.dto.response.FeedSearchResponse;
import ignis.ignis.domain.feed.controller.dto.response.FindAllFeedResponse;
import ignis.ignis.domain.feed.controller.dto.response.FindFeedResponse;
import ignis.ignis.domain.feed.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Feed", description = "게시글")
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    @Operation(description = "게시글 생성")
    @PostMapping
    public void create(@RequestPart(name = "title") String title, @RequestPart("file") List<MultipartFile> file, @RequestPart XYRequest request) throws IOException {
        feedService.createFeed(title, file, request);
    }

    @Operation(description = "1km 조회")
    @GetMapping("/all")
    public List<FindAllFeedResponse> all(@RequestParam double x,@RequestParam double y) {
        return feedService.getFeed(x, y);
    }

    @Operation(description = "검색")
    @GetMapping
    public FeedSearchResponse query(@RequestParam String title) {
        return feedService.queryFindFeed(title);
    }

    @Operation(description = "상세조회")
    @GetMapping("/{feedId}")
    public FindFeedResponse findFeed(@PathVariable Long feedId) {
        return feedService.queryFeed(feedId);
    }

    @Operation(description = "좋아요")
    @PostMapping("/add/count/{feedId}")
    public CountResponse addCount(@PathVariable Long feedId) {
        return feedService.addCount(feedId);
    }

    @Operation(description = "싫어요")
    @DeleteMapping("/delete/count/{feedId}")
    public CountResponse deleteCount(@PathVariable Long feedId) {
        return feedService.deleteCount(feedId);
    }

    @Operation(description = "게시글 삭제")
    @DeleteMapping("/{feedId}")
    public void deleteFeed(@PathVariable Long feedId) {
        feedService.deleteFeed(feedId);
    }
}
