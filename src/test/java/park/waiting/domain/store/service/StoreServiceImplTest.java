package park.waiting.domain.store.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import park.waiting.domain.store.dto.StoreRequest;
import park.waiting.domain.store.dto.StoreResponse;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.repository.StoreRepository;
import park.waiting.domain.store.status.OpenStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

    @InjectMocks
    StoreServiceImpl storeService;

    @Mock
    StoreRepository storeRepository;

    @Test
    @DisplayName("가게 등록 성공")
    void add_store() {
        // Given
        Store store = Store.builder()
                .name("가게1")
                .phoneNumber("02-1234-5678")
                .openHours("10am")
                .openStatus(OpenStatus.valueOf("OPEN"))
                .address("서울시 성북구 xxx")
                .build();
        store.setId(1L);

        StoreRequest storeRequest = StoreRequest.builder()
                .name("가게1")
                .phoneNumber("02-1234-5678")
                .openHours("10am")
                .openStatus(OpenStatus.valueOf("OPEN"))
                .address("서울시 성북구 xxx")
                .build();

        when(storeRepository.save(any(Store.class))).thenReturn(store);
        // When
        StoreResponse result = storeService.addStore(storeRequest);

        // Then
        assertThat(result.getName()).isEqualTo("가게1");
    }

}