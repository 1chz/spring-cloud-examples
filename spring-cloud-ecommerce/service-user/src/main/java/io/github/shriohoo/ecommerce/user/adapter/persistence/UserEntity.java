package io.github.shriohoo.ecommerce.user.adapter.persistence;

import io.github.shriohoo.ecommerce.user.domain.User;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;
    private String password;
    private String encryptedPassword;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    private UserEntity(String email, String username, String password, String encryptedPassword, LocalDateTime createdAt) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.encryptedPassword = encryptedPassword;
        this.createdAt = createdAt;
    }

    public static UserEntity convert(User user) {
        return UserEntity.builder()
            .email(user.getEmail())
            .username(user.getUsername())
            .password(user.getPassword())
            .encryptedPassword(user.getEncryptedPassword())
            .createdAt(user.getCreatedAt())
            .build();
    }

    public User toUser() {
        return User.create(email, username, null, null);
    }

}
