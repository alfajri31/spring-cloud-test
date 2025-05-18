package org.group.authbackend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_users")
@Component
@Getter
@Setter
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    public String id;
    @Column(name="email")
    public String email;
    @Getter
    @Column(name="username")
    public String username;
    @Getter
    @Column(name="password")
    public String password;
    @Column(name="role")
    public Integer role;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
