package park.waiting.domain.store.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.common.constant.ErrorCode;
import park.waiting.common.exception.GeneralException;
import park.waiting.domain.store.dto.StoreResponse;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StoreResponse> getStores() {
        try {
            return storeRepository.findAll()
                    .stream()
                    .map(Store::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public StoreResponse getStore(Long storeId) {
        try {
            return storeRepository.findById(storeId)
                    .map(Store::toResponse)
                    .orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }
}
