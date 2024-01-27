package ignis.ignis.domain.feed.service;

import com.amazonaws.services.kms.model.NotFoundException;
import ignis.ignis.domain.feed.controller.dto.request.XYRequest;
import ignis.ignis.domain.feed.controller.dto.response.CountResponse;
import ignis.ignis.domain.feed.controller.dto.response.FindAllFeedResponse;
import ignis.ignis.domain.feed.controller.dto.response.FindFeedResponse;
import ignis.ignis.domain.feed.domain.Count;
import ignis.ignis.domain.feed.domain.CountRepository;
import ignis.ignis.domain.feed.domain.Feed;
import ignis.ignis.domain.feed.domain.repository.FeedRepository;
import ignis.ignis.domain.user.domain.User;
import ignis.ignis.domain.user.facade.UserFacade;
import ignis.ignis.infra.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;
    private final S3Service s3Service;
    private final CountRepository countRepository;

    @Transactional
    public void createFeed(String title, List<MultipartFile> image, XYRequest request) {
        User user = userFacade.getCurrentUser();

        if (image.isEmpty()) {
            throw new RuntimeException("null");
        }
        String imageUrl = s3Service.uploadImage(image);
        LocalDateTime createAt = LocalDateTime.now();
        feedRepository.save(
                Feed.builder()
                        .user(user)
                        .title(title)
                        .imageUrl(imageUrl)
                        .createAt(createAt)
                        .count(0)
                        .x(request.getX())
                        .y(request.getY())
                        .build()
        );
        user.addRe();
    }

    @Transactional(readOnly = true)
    public List<FindAllFeedResponse> getFeed(double userLatitude, double userLongitude) {
        List<Feed> feed = feedRepository.findFeedsNearUser(userLatitude, userLongitude);
        return map(feed);
    }

    private List<FindAllFeedResponse> map(List<Feed> feeds) {
        return feeds.stream()
                .map(FindAllFeedResponse::findAllFeedResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CountResponse addCount(Long feedId) {
        User user = userFacade.getCurrentUser();
        Feed feed = feedRepository.findById(feedId).orElseThrow(()->new NotFoundException("adsf"));
        if(countRepository.existsByUserAndFeed(user, feed)) {
            throw new RuntimeException(("adsf"));
        }
        countRepository.save(
                Count.builder()
                        .user(user)
                        .feed(feed)
                        .build());

        feed.addCount();
        return new CountResponse(feed.getCount());
    }

    @Transactional
    public CountResponse deleteCount(Long feedId) {
        User user = userFacade.getCurrentUser();
        Feed feed = feedRepository.findById(feedId).orElseThrow(()->new NotFoundException("adsf"));
        if (!countRepository.existsByUserAndFeed(user, feed)) {
            throw new RuntimeException("adsf");
        }
        countRepository.deleteByUserAndFeed(user, feed);

        feed.deleteCount();
        return new CountResponse(feed.getCount());
    }

    @Transactional(readOnly = true)
    public List<FindAllFeedResponse> queryFindFeed(String title) {
        return feedRepository.findByTitleContains(title).stream().map(FindAllFeedResponse::findAllFeedResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FindFeedResponse queryFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new RuntimeException("null"));
        return new FindFeedResponse(feed);
    }

    @Transactional
    public void deleteFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(()-> new RuntimeException("s"));
        feedRepository.delete(feed);
    }
}
