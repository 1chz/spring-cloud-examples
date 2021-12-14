package io.github.shriohoo.ecommerce.user.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private String email;
    private String username;
    private String password;
    private final LocalDateTime createdAt;

    @Builder
    private User(String email, String username, String password, LocalDateTime createdAt) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

}
