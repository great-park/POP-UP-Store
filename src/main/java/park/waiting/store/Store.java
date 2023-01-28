package park.waiting.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import park.waiting.store.status.OpenStatus;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String name;
    private String phoneNumber;
    private OpenStatus openStatus;
    private int openHours;
    private String address;
    private String description;

    protected Store() {}

    protected Store(String name, String phoneNumber, OpenStatus openStatus, int openHours, String address, String description) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.openStatus = openStatus;
        this.openHours = openHours;
        this.address = address;
        this.description = description;
    }

    public static Store of(String name, String phoneNumber, OpenStatus openStatus, int openHours, String address, String description) {
        return new Store(name, phoneNumber, openStatus, openHours, address, description);
    }
}
