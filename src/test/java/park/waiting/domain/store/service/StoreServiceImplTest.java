package park.waiting.domain.store.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import park.waiting.domain.store.dto.*;
import park.waiting.domain.store.entity.Product;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.store.repository.ProductRepository;
import park.waiting.domain.store.repository.StoreRepository;
import park.waiting.domain.store.status.OpenStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

    @InjectMocks
    StoreServiceImpl storeService;

    @Mock
    StoreRepository storeRepository;

    @Mock
    ProductRepository productRepository;

    @Test
    @DisplayName("가게 등록 성공")
    void add_store() {
        // Given
        Store store = getStore();

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
        assertThat(result.getPhoneNumber()).isEqualTo("02-1234-5678");
        assertThat(result.getOpenHours()).isEqualTo("10am");
        assertThat(result.getOpenStatus()).isEqualTo("OPEN");
        assertThat(result.getAddress()).isEqualTo("서울시 성북구 xxx");
    }

    @Test
    @DisplayName("가게 수정 성공")
    public void update_Store() {
        // Given
        Store store = getStore();

        StoreRequest storeUpdateRequest = StoreRequest.builder()
                .name("수정된 가게이름")
                .phoneNumber("031-9999-1111")
                .openHours("12am")
                .openStatus(OpenStatus.valueOf("OPEN"))
                .address("서울시 은평구 xxx")
                .build();

        when(storeRepository.save(any(Store.class))).thenReturn(store);
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        // When
        StoreResponse result = storeService.updateStore(1L, storeUpdateRequest);

        // Then
        assertThat(result.getName()).isEqualTo("수정된 가게이름");
        assertThat(result.getPhoneNumber()).isEqualTo("031-9999-1111");
        assertThat(result.getOpenHours()).isEqualTo("12am");
        assertThat(result.getOpenStatus()).isEqualTo("OPEN");
        assertThat(result.getAddress()).isEqualTo("서울시 은평구 xxx");
    }

    @Test
    @DisplayName("상품 등록 성공")
    public void add_Product() {
        // Given
        Store store = getStore();
        Product product = getProduct(store);
        ProductAddRequest productRequest = getProductAddRequest(store);

        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        doReturn(product).when(productRepository).save(any(Product.class));

        // When
        ProductResponse result = storeService.addProduct(productRequest);

        // Then
        assertThat(result.getProductId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("노트북");
        assertThat(result.getPrice()).isEqualTo(980000);
        assertThat(result.getDescription()).isEqualTo("22년 신상품");
    }

    @Test
    @DisplayName("상품 수정 성공")
    public void update_Product() {
        // Given
        Store store = getStore();
        Product product = getProduct(store);

        ProductRequest updateRequest = ProductRequest.builder()
                .storeId(store.getId())
                .productId(product.getId())
                .name("데스크탑")
                .price(1000000)
                .description("모니터 증정")
                .build();

        doReturn(Optional.of(product)).when(productRepository).findById(1L);

        // When
        ProductResponse result = storeService.updateProduct(updateRequest);

        // Then
        assertThat(result.getName()).isEqualTo("데스크탑");
    }

    private ProductAddRequest getProductAddRequest(Store store) {
        return ProductAddRequest.builder()
                .storeId(store.getId())
                .name("노트북")
                .price(980000)
                .description("22년 신상품")
                .build();
    }

    private Product getProduct(Store store) {
        Product product = Product.builder()
                .store(store)
                .name("노트북")
                .price(980000)
                .description("22년 신상품")
                .build();
        product.setId(1L);
        return product;
    }

    private Store getStore() {
        Store store = Store.builder()
                .name("가게1")
                .phoneNumber("02-1234-5678")
                .openHours("10am")
                .openStatus(OpenStatus.valueOf("OPEN"))
                .address("서울시 성북구 xxx")
                .build();
        store.setId(1L);
        return store;
    }

}