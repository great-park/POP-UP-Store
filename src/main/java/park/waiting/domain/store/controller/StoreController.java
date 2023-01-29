package park.waiting.domain.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import park.waiting.common.dto.ApiDataResponse;
import park.waiting.domain.store.dto.StoreAddRequest;
import park.waiting.domain.store.dto.StoreRequest;
import park.waiting.domain.store.dto.StoreResponse;
import park.waiting.domain.store.service.StoreService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ApiDataResponse<List<StoreResponse>> getStores() {
        List<StoreResponse> stores = storeService.getStores();
        return ApiDataResponse.of(stores);
    }

    @GetMapping("/{storeId}")
    public ApiDataResponse<StoreResponse> getStore(
            @PathVariable("storeId") Long storeId
    ) {
        StoreResponse store = storeService.getStore(storeId);
        return ApiDataResponse.of(store);
    }

    @PostMapping
    public ApiDataResponse<StoreResponse> addStore(
            @RequestBody StoreAddRequest storeAddRequest
    ) {
        // Todo: token 통해서 managerId가져오기. 현재는 임시로 설정
        long tempManagerId = 1; //임시 대체

        StoreRequest storeRequest = StoreRequest.of(tempManagerId, storeAddRequest);
        StoreResponse storeResponse = storeService.addStore(storeRequest);

        return ApiDataResponse.of(storeResponse);
    }
}
