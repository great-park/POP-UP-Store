package park.waiting.domain.posting.service;

import park.waiting.domain.posting.dto.PostingRequest;
import park.waiting.domain.posting.dto.PostingResponse;

public interface PostingService {

    PostingResponse create(PostingRequest postingRequest);

    PostingResponse update(PostingRequest postingRequest);

    PostingResponse getByPostingId(Long postingId);
}
