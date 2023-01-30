package park.waiting.domain.queue.entity;

import lombok.*;
import park.waiting.common.entity.BaseEntity;
import park.waiting.domain.queue.dto.QueueResponse;
import park.waiting.domain.queue.status.WaitingStatus;
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
public class Waiting extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    private Long waitingCount; // 앞에 남아있는 대기 수

    private WaitingStatus waitingStatus;

    public static Waiting of(Customer customer, Store store, Long waitingCount) {
        return Waiting.builder()
                .customer(customer)
                .store(store)
                .waitingCount(waitingCount)
                .waitingStatus(WaitingStatus.WAITING)
                .build();
    }

    public QueueResponse toResponse() {
        return QueueResponse.builder()
                .id(getId())
                .storeName(store.getName())
                .customerName(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .waitingCount(waitingCount)
                .waitingStatus(waitingStatus)
                .build();
    }

    public Waiting moveForward() {
        this.waitingCount -= 1;
        return this;
    }

    public void done() {
        this.waitingStatus = WaitingStatus.DONE;
    }

    public void cancel() {
        this.waitingStatus = WaitingStatus.CANCELED;
    }
}
