package park.waiting.domain.queue.entity;

import lombok.*;
import park.waiting.common.entity.BaseEntity;
import park.waiting.domain.queue.dto.QueueResponse;
import park.waiting.domain.queue.status.QueueStatus;
import park.waiting.domain.store.entity.Store;
import park.waiting.domain.user.entity.Customer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Setter(AccessLevel.NONE)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Queue extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private Long waitingCount;

    private QueueStatus queueStatus;

    public static Queue of(Customer customer, Store store) {
        return Queue.builder()
                .customer(customer)
                .store(store)
                .queueStatus(QueueStatus.WAITING)
                .build();
    }

    public QueueResponse toResponse() {
        return QueueResponse.builder()
                .id(getId())
                .storeName(store.getName())
                .customerName(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .waitingCount(waitingCount)
                .queueStatus(queueStatus)
                .build();
    }
}
