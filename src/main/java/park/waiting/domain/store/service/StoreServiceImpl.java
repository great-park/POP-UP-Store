package park.waiting.domain.store.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.common.constant.ErrorCode;
import park.waiting.common.exception.GeneralException;
import park.waiting.domain.store.dto.*;
import park.waiting.domain.store.entity.Product;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.repository.ProductRepository;
import park.waiting.domain.store.repository.StoreRepository;
import park.waiting.domain.store.status.OpenStatus;
import park.waiting.domain.user.repository.ManagerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;
    private final ProductRepository productRepository;

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
        Store addedStore = Store.builder()
                .address(storeRequest.getAddress())
                .name(storeRequest.getName())
                .openStatus(storeRequest.getOpenStatus())
                .openHours(storeRequest.getOpenHours())
                .phoneNumber(storeRequest.getPhoneNumber())
                .build();
//        manager.setStore(store);
        return storeRepository.save(addedStore).toResponse();
    }

    @Override
    @Transactional
    public StoreResponse updateStore(Long storeId, StoreRequest storeRequest) {
        Store updatedStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        updatedStore.update(storeRequest);

        return storeRepository.save(updatedStore).toResponse();
    }

    @Override
    @Transactional
    public StoreResponse removeStore(Long storeId) {
        Store removedStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        removedStore.inactivate();

        return removedStore.toResponse();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getProducts(Long storeId) {
        return productRepository.findByStoreId(storeId).stream()
                .filter(Product::isActive)
                .map(Product::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponse addProduct(ProductAddRequest productAddRequest) {
        Store store = storeRepository.findById(productAddRequest.getStoreId())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        Product addedProduct = Product.builder()
                .store(store)
                .name(productAddRequest.getName())
                .price(productAddRequest.getPrice())
                .description(productAddRequest.getDescription())
                .build();
        return productRepository.save(addedProduct).toResponse();
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(ProductRequest productRequest) {
        Product updateProduct = productRepository.findById(productRequest.getProductId())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        updateProduct.update(productRequest);
        return productRepository.save(updateProduct).toResponse();
    }

    @Override
    public ProductResponse removeProduct(Long productId) {
        Product removedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        removedProduct.inactivate();

        return removedProduct.toResponse();
    }
}
