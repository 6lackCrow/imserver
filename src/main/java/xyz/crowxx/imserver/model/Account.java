package xyz.crowxx.imserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"phone"})
        }
)
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 130)
    private String phone;
    private String name;
    @JsonIgnore
    private String password;
    private String bio;
    private String avatar;
    private LocalDate birthday;
    private Integer gender;
    @CreatedDate
    private LocalDateTime createdDate;
}
