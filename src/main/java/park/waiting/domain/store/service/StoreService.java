package park.waiting.domain.store.service;

import park.waiting.domain.store.dto.*;

import java.util.List;

public interface StoreService {

    List<StoreResponse> getStores();

    StoreResponse getStore(Long storeId);

    StoreResponse addStore(StoreRequest storeRequest);

    StoreResponse updateStore(Long storeId, StoreRequest storeRequest);

    StoreResponse removeStore(Long storeId);

    List<ProductResponse> getProducts(Long storeId);

    ProductResponse addProduct(ProductAddRequest productAddRequest);

    ProductResponse updateProduct(ProductRequest productRequest);

    ProductResponse removeProduct(Long productId);
}
