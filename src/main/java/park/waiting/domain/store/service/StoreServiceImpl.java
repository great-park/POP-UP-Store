package park.waiting.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.domain.store.dto.StoreDto;
import park.waiting.domain.store.dto.StoreResponse;
import park.waiting.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StoreDto> getStoreList() {
        return storeRepository.findAll()
                .stream()
                .map(StoreDto::of)
                .collect(Collectors.toList());
    }
}
