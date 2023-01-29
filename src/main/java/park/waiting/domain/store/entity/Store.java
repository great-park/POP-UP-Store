package park.waiting.domain.store.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import park.waiting.domain.store.status.OpenStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Setter
    @Column(nullable = false)
    private OpenStatus openStatus;

    @Setter
    @Column(nullable = false)
    private Integer openHours;

    @Setter
    @Column(nullable = false)
    private String address;

    @Setter
    @Column(nullable = false)
    private String description;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    protected Store() {}

    protected Store(String name, String phoneNumber, OpenStatus openStatus, Integer openHours, String address, String description) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.openStatus = openStatus;
        this.openHours = openHours;
        this.address = address;
        this.description = description;
    }

    public static Store of(String name, String phoneNumber, OpenStatus openStatus, Integer openHours, String address, String description) {
        return new Store(name, phoneNumber, openStatus, openHours, address, description);
    }
}
