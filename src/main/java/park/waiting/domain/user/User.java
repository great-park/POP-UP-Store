package park.waiting.domain.user;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String phoneNumber;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected User() {}

    protected User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // static factory method로 사용하기 위해 생성자 lombok 사용 x
    public static User of(String phoneNumber) {
        return new User(phoneNumber);
    }

}
