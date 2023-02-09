package park.waiting.domain.posting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import park.waiting.common.dto.ApiDataResponse;
import park.waiting.domain.posting.dto.PostingDeleteRequest;
import park.waiting.domain.posting.dto.PostingRequest;
import park.waiting.domain.posting.dto.PostingResponse;
import park.waiting.domain.posting.service.PostingService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posting")
public class PostingController {

    private final PostingService postingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiDataResponse<PostingResponse> create(
            @RequestBody PostingRequest postingRequest
    ) {
        // 인증 도입 이후에 PostingRequest 대신 내용만 담는 Dto 만들기
        return ApiDataResponse.of(postingService.create(postingRequest));
    }

    @GetMapping("{postingId}")
    public ApiDataResponse<PostingResponse> getPosting(
            @PathVariable Long postingId
    ) {
        return ApiDataResponse.of(postingService.getByPostingId(postingId));
    }

    @GetMapping("/customer/{customerId}")
    public ApiDataResponse<List<PostingResponse>> getCustomerPostingList(
            @PathVariable Long customerId
    ){
        return ApiDataResponse.of(postingService.getListByCustomerId(customerId));
    }

    @GetMapping("/store/{storeId}")
    public ApiDataResponse<List<PostingResponse>> getStorePoistingList(
            @PathVariable Long storeId
    ) {
        return ApiDataResponse.of(postingService.getListByStoreId(storeId));
    }

    @DeleteMapping("/delete")
    public ApiDataResponse<PostingResponse> deletePosting(
            @RequestBody PostingDeleteRequest postingDeleteRequest
    ) {
        return ApiDataResponse.of(postingService.delete(postingDeleteRequest));
    }

    @PatchMapping("/update")
    public ApiDataResponse<PostingResponse> updatePosting(
            @RequestBody PostingRequest postingRequest
    ) {
        return ApiDataResponse.of(postingService.update(postingRequest));
    }
}
