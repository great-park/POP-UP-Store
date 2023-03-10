package park.waiting.app.posting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.app.common.constant.ErrorCode;
import park.waiting.app.common.exception.GeneralException;
import park.waiting.app.posting.dto.PostingDeleteRequest;
import park.waiting.app.posting.dto.PostingRequest;
import park.waiting.app.posting.dto.PostingResponse;
import park.waiting.app.posting.entity.Posting;
import park.waiting.app.posting.repository.PostingRepository;
import park.waiting.app.store.entity.Store;
import park.waiting.app.store.repository.StoreRepository;
import park.waiting.app.user.entity.Customer;
import park.waiting.app.user.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        Posting posting = postingRepository.findFirstByStoreIdAndCustomerIdOrderByIdDesc(postingRequest.getStoreId(), postingRequest.getCustomerId())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        posting.update(postingRequest);
        return posting.toResponse();
    }

    @Transactional
    @Override
    public PostingResponse delete(PostingDeleteRequest postingDeleteRequest) {
        Posting posting = postingRepository.findById(postingDeleteRequest.getPostingId())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        posting.inactivate();
        return posting.toResponse();
    }

    @Transactional(readOnly = true)
    @Override
    public PostingResponse getByPostingId(Long postingId) {
        return postingRepository.findByIdAndActiveIsTrue(postingId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR))
                .toResponse();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostingResponse> getListByStoreId(Long storeId) {
        return postingRepository.findByIdAndActiveIsTrue(storeId)
                .stream()
                .map(Posting::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostingResponse> getListByCustomerId(Long customerId) {
        return postingRepository.findAllByCustomerIdAndActiveIsTrue(customerId)
                .stream()
                .map(Posting::toResponse)
                .collect(Collectors.toList());
    }
}
