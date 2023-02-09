package park.waiting.domain.posting.service;

import park.waiting.domain.posting.dto.PostingDeleteRequest;
import park.waiting.domain.posting.dto.PostingRequest;
import park.waiting.domain.posting.dto.PostingResponse;

import java.util.List;

public interface PostingService {

    PostingResponse create(PostingRequest postingRequest);

    PostingResponse update(PostingRequest postingRequest);

    PostingResponse delete(PostingDeleteRequest postingDeleteRequest);

    PostingResponse getByPostingId(Long postingId);

    List<PostingResponse> getListByStoreId(Long storeId);

    List<PostingResponse> getListByCustomerId(Long customerId);
}
