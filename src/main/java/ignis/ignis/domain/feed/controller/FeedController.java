package ignis.ignis.domain.feed.controller;

import ignis.ignis.domain.feed.controller.dto.request.XYRequest;
import ignis.ignis.domain.feed.controller.dto.response.CountResponse;
import ignis.ignis.domain.feed.controller.dto.response.FindAllFeedResponse;
import ignis.ignis.domain.feed.controller.dto.response.FindFeedResponse;
import ignis.ignis.domain.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public void create(@RequestPart(name = "title") String title, @RequestPart("file") List<MultipartFile> file, @RequestPart XYRequest request) {
        feedService.createFeed(title, file, request);
    }

    @GetMapping("/all")
    public List<FindAllFeedResponse> all(@RequestParam double x,@RequestParam double y) {
        return feedService.getFeed(x, y);
    }

    @GetMapping
    public List<FindAllFeedResponse> query(@RequestParam String title) {
        return feedService.queryFindFeed(title);
    }

    @GetMapping("/{feedId}")
    public FindFeedResponse findFeed(@PathVariable Long feedId) {
        return feedService.queryFeed(feedId);
    }

    @PostMapping("/add/count/{feedId}")
    public CountResponse addCount(@PathVariable Long feedId) {
        return feedService.addCount(feedId);
    }

    @DeleteMapping("/delete/count/{feedId}")
    public CountResponse deleteCount(@PathVariable Long feedId) {
        return feedService.deleteCount(feedId);
    }
}
