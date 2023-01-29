package park.waiting.domain.store.service;

import park.waiting.domain.store.dto.StoreDto;

import java.util.List;

public interface StoreService {

    List<StoreDto> getStoreList();
}
