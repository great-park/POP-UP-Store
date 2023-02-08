package park.waiting.domain.posting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.domain.posting.dto.PostingRequest;
import park.waiting.domain.posting.dto.PostingResponse;
import park.waiting.domain.posting.repository.PostingRepository;
import park.waiting.domain.store.repository.StoreRepository;

@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService{

    private final PostingRepository postingRepository;
    private final StoreRepository storeRepository;


    @Transactional
    @Override
    public PostingResponse create(PostingRequest postingRequest) {
        return null;
    }

    @Transactional
    @Override
    public PostingResponse update(PostingRequest postingRequest) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public PostingResponse getByPostingId(Long postingId) {
        return null;
    }
}
