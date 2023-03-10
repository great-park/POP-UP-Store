package park.waiting.app.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import park.waiting.app.common.dto.ApiDataResponse;
import park.waiting.app.store.dto.*;
import park.waiting.app.store.service.StoreService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ApiDataResponse<List<StoreResponse>> getStores() {
        return ApiDataResponse.of(
                storeService.getStores()
        );
    }

    @GetMapping("/{storeId}")
    public ApiDataResponse<StoreResponse> getStore(
            @PathVariable("storeId") Long storeId
    ) {
        return ApiDataResponse.of(
                storeService.getStore(storeId)
        );
    }

    @PostMapping
    public ApiDataResponse<StoreResponse> addStore(
            @RequestBody StoreAddRequest storeAddRequest
    ) {
        // Todo: token 통해서 managerId가져오기. 현재는 임시로 설정
        long tempManagerId = 1; //임시 대체

        return ApiDataResponse.of(
                storeService.addStore(StoreRequest.of(tempManagerId, storeAddRequest))
        );
    }

    @PatchMapping("/update/{storeId}")
    public ApiDataResponse<StoreResponse> updateStore(
            @PathVariable("storeId") Long storeId,
            @RequestBody StoreRequest storeRequest
    ) {
        return ApiDataResponse.of(
                storeService.updateStore(storeId, storeRequest)
        );
    }

    @DeleteMapping("/delete/{storeId}")
    public ApiDataResponse<StoreResponse> removeStore(
            @PathVariable("storeId") Long storeId
    ) {
        return ApiDataResponse.of(
                storeService.removeStore(storeId)
        );
    }

    @GetMapping("/products")
    public ApiDataResponse<List<ProductResponse>> getProducts(
            @RequestParam("storeId") Long storeId
    ) {
        return ApiDataResponse.of(
                storeService.getProducts(storeId)
        );
    }

    @PostMapping("/product")
    public ApiDataResponse<ProductResponse> addProduct(
            @RequestBody ProductAddRequest productAddRequest
    ) {
        return ApiDataResponse.of(
                storeService.addProduct(productAddRequest)
        );
    }

    @PatchMapping("/product")
    public ApiDataResponse<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest
    ) {
        return ApiDataResponse.of(
                storeService.updateProduct(productRequest)
        );
    }

    @DeleteMapping("/product/{productId}")
    public ApiDataResponse<ProductResponse> removeProduct(
            @PathVariable("productId") Long productId
    ) {
        return ApiDataResponse.of(
                storeService.removeProduct(productId)
        );
    }

}
