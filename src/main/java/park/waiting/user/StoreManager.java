package park.waiting.user;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import park.waiting.store.Store;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class StoreManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String phoneNumber;
    private String email;
    private String password;

    @OneToOne
    @Setter
    private Store store;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected StoreManager() {}

    protected StoreManager(String phoneNumber, String email, String password, Store store){
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.store = store;
    }

    public StoreManager of(String phoneNumber, String email, String password, Store store){
        return new StoreManager(phoneNumber, email, password, store);
    }
}
