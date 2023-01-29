package park.waiting.domain.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.waiting.common.dto.ApiDataResponse;
import park.waiting.domain.store.dto.StoreDto;
import park.waiting.domain.store.dto.StoreResponse;
import park.waiting.domain.store.service.StoreService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ApiDataResponse<List<StoreResponse>> getStores() {
        List<StoreDto> storeList = storeService.getStoreList();
        return ApiDataResponse.of(
                storeList.stream()
                        .map(StoreResponse::from)
                        .collect(Collectors.toList())
        );
    }


}
