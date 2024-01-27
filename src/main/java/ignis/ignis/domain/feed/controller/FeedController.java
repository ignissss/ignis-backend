package ignis.ignis.domain.feed.controller;

import ignis.ignis.domain.feed.controller.dto.response.CountResponse;
import ignis.ignis.domain.feed.controller.dto.response.FindAllFeedResponse;
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
    public void create(@RequestPart(name = "title") String title, @RequestPart("file") List<MultipartFile> file) {
        feedService.createFeed(title, file);
    }

    @GetMapping("/all")
    public List<FindAllFeedResponse> all() {
        return feedService.queryAllFeed();
    }

    @GetMapping
    public List<FindAllFeedResponse> query(@RequestParam String title) {
        return feedService.queryFindFeed(title);
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
