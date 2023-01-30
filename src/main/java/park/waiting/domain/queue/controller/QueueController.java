package park.waiting.domain.queue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import park.waiting.common.dto.ApiDataResponse;
import park.waiting.domain.queue.dto.QueueFixRequest;
import park.waiting.domain.queue.dto.QueueRequest;
import park.waiting.domain.queue.dto.QueueResponse;
import park.waiting.domain.queue.service.QueueService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/queue")
public class QueueController {

    private final QueueService queueService;

    @GetMapping("/{storeId}")
    public ApiDataResponse<List<QueueResponse>> getList(
            @PathVariable("storeId") Long storeId
    ) {
        return ApiDataResponse.of(
                queueService.getByStoreId(storeId)
        );
    }

    @GetMapping("/mine/{customerId}")
    public ApiDataResponse<QueueResponse> get(
            @PathVariable("customerId") Long customerId
    ) {
        return ApiDataResponse.of(
                queueService.getByCustomerId(customerId)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiDataResponse<QueueResponse> append(
            @RequestBody QueueRequest queueRequest
    ) {
        return ApiDataResponse.of(
                queueService.appendTo(queueRequest)
        );
    }

    @PatchMapping
    public ApiDataResponse<QueueResponse> done(
            @RequestBody QueueFixRequest queueFixRequest
    ) {
        return ApiDataResponse.of(
                queueService.done(queueFixRequest.getStoreId())
        );
    }

    @DeleteMapping
    public ApiDataResponse<QueueResponse> cancel(
            @RequestBody QueueFixRequest queueFixRequest
    ) {
        return ApiDataResponse.of(
                queueService.cancel(queueFixRequest.getStoreId())
        );
    }
}
