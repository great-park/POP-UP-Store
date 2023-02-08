package park.waiting.domain.posting.entity;

import lombok.*;
import park.waiting.common.entity.BaseEntity;
import park.waiting.domain.posting.dto.PostingRequest;
import park.waiting.domain.posting.dto.PostingResponse;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.user.entity.Customer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Posting extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private String title;

    private String contents;

    public static Posting of(Customer customer, Store store, PostingRequest postingRequest) {
        return Posting.builder()
                .customer(customer)
                .store(store)
                .title(postingRequest.getTitle())
                .contents(postingRequest.getContents())
                .build();
    }

    public PostingResponse toResponse() {
        return PostingResponse.builder()
                .id(getId())
                .customerId(customer.getId())
                .customerName(customer.getName())
                .storeId(store.getId())
                .storeName(store.getName())
                .title(title)
                .contents(contents)
                .build();
    }

    public void update(PostingRequest postingRequest) {
        title = postingRequest.getTitle();
        contents = postingRequest.getContents();
    }

}
