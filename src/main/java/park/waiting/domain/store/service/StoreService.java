package park.waiting.domain.store.service;

import park.waiting.domain.store.dto.StoreResponse;

import java.util.List;

public interface StoreService {

    List<StoreResponse> getStores();

    StoreResponse getStore(Long storeId);
}
