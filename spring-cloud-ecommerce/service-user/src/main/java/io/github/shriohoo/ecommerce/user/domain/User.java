package io.github.shriohoo.ecommerce.user.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class User {

    private String email;
    private String username;
    private String password;
    private final LocalDateTime createdAt;

    private User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public static User create(String email, String username, String password) {
        return new User(email, username, password);
    }

}
