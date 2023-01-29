package park.waiting.domain.user.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import park.waiting.domain.store.entity.Store;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Entity
@EntityListeners(AuditingEntityListener.class)
public class StoreManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Setter
    @Column(nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    @OneToOne
    @Setter
    private Store store;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

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
