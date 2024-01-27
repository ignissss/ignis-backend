package ignis.ignis.domain.feed.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedSearchResponse {
    private Integer count;
    private List<FindAllFeedResponse> feeds;
}
