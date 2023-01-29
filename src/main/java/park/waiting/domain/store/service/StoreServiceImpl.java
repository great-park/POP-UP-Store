package park.waiting.domain.store.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.common.constant.ErrorCode;
import park.waiting.common.exception.GeneralException;
import park.waiting.domain.store.dto.StoreRequest;
import park.waiting.domain.store.dto.StoreResponse;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.repository.StoreRepository;
import park.waiting.domain.store.status.OpenStatus;
import park.waiting.domain.user.entity.Manager;
import park.waiting.domain.user.repository.ManagerRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StoreResponse> getStores() {
        return storeRepository.findAll()
                .stream()
                .filter(Store::isActive)
                .map(Store::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StoreResponse getStore(Long storeId) {
        return storeRepository.findById(storeId)
                .filter(Store::isActive)
                .map(Store::toResponse)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
    }

    @Override
    @Transactional
    public StoreResponse addStore(StoreRequest storeRequest) {
        // 아직 인증 구현이 안 되었으므로 비활성화
//        Manager manager = managerRepository.findById(storeRequest.getManagerId())
//                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        Store addStore = Store.builder()
                .address(storeRequest.getAddress())
                .name(storeRequest.getName())
                .openStatus(OpenStatus.valueOf(storeRequest.getOpenStatus()))
                .openHours(storeRequest.getOpenHours())
                .phoneNumber(storeRequest.getPhoneNumber())
                .build();
        Store store = storeRepository.save(addStore);
//        manager.setStore(store);
        return store.toResponse();
    }

    @Override
    @Transactional
    public StoreResponse updateStore(Long storeId, StoreRequest storeRequest) {
        Store updateStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        updateStore.update(storeRequest);
        storeRepository.save(updateStore);

        return updateStore.toResponse();
    }

    @Override
    @Transactional
    public StoreResponse removeStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        store.inactivate();

        return store.toResponse();
    }
}
