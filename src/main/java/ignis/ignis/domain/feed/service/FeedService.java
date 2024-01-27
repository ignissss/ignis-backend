package ignis.ignis.domain.feed.service;

import com.amazonaws.services.kms.model.NotFoundException;
import ignis.ignis.domain.feed.controller.dto.request.XYRequest;
import ignis.ignis.domain.feed.controller.dto.response.CountResponse;
import ignis.ignis.domain.feed.controller.dto.response.FeedSearchResponse;
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

import java.io.IOException;
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
    public void createFeed(String title, List<MultipartFile> images, XYRequest request) throws IOException {
        User user = userFacade.getCurrentUser();

        if (images.isEmpty()) {
            throw new RuntimeException("Image list is empty");
        }

        List<String> imageUrls = s3Service.uploadImages(images);

        LocalDateTime createAt = LocalDateTime.now();

        for (String imageUrl : imageUrls) {
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
        }

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
    public CountResponse like(Long feedId) {
        User user = userFacade.getCurrentUser();
<<<<<<< HEAD
        Feed feed = feedRepository.findById(feedId).orElseThrow(()->new RuntimeException("asdf"));
        if(countRepository.existsByUserAndFeed(user, feed)) {
            throw new RuntimeException(("adsf"));
=======
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new NotFoundException("asdf"));
        boolean like = countRepository.existsByUserAndFeed(user, feed);
        if (like) {
            countRepository.deleteByUserAndFeed(user, feed);
            feed.deleteCount();
        } else {
            countRepository.save(
                    Count.builder()
                            .user(user)
                            .feed(feed)
                            .build());
            feed.addCount();
>>>>>>> refs/remotes/origin/master
        }
        return new CountResponse(feed.getCount());
    }



    @Transactional(readOnly = true)
    public FeedSearchResponse queryFindFeed(String title) {
        return FeedSearchResponse.builder()
                .feeds(feedRepository.findByTitleContains(title).stream().map(FindAllFeedResponse::findAllFeedResponse).collect(Collectors.toList()))
                .count(feedRepository.countByTitleContains(title))
                .build();
    }

    @Transactional(readOnly = true)
    public FindFeedResponse queryFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new RuntimeException("null"));
        return new FindFeedResponse(feed, countRepository.existsByUser(feed.getUser()));
    }

    @Transactional
    public void deleteFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(()-> new RuntimeException("s"));
        if (!userFacade.getCurrentUser().getEmail().equals(feed.getUser().getEmail())) {
            throw new RuntimeException("asdf");
        }
        feedRepository.delete(feed);
    }
}
