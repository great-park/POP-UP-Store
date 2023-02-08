package park.waiting.domain.posting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.common.constant.ErrorCode;
import park.waiting.common.exception.GeneralException;
import park.waiting.domain.posting.dto.PostingRequest;
import park.waiting.domain.posting.dto.PostingResponse;
import park.waiting.domain.posting.entity.Posting;
import park.waiting.domain.posting.repository.PostingRepository;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.repository.StoreRepository;
import park.waiting.domain.user.entity.Customer;
import park.waiting.domain.user.repository.CustomerRepository;

@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService{

    private final PostingRepository postingRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;


    @Transactional
    @Override
    public PostingResponse create(PostingRequest postingRequest) {
        Store store = storeRepository.findById(postingRequest.getStoreId())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        Customer customer = customerRepository.findById(postingRequest.getCustomerId())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        Posting posting = Posting.of(customer, store, postingRequest);
        Posting saved = postingRepository.save(posting);

        return saved.toResponse();
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
