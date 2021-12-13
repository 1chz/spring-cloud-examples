package io.github.shriohoo.ecommerce.user.adapter.web;

import io.github.shriohoo.ecommerce.user.domain.User;
import io.github.shriohoo.ecommerce.user.service.UserCrudService;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCrudService userCrudService;

    @PostMapping
    public ResponseEntity<ResponseUser> create(@RequestBody RequestUser requestUser) {
        User user = userCrudService.createUser(requestUser.toUser());
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponseUser.convert(user));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody RequestUser requestUser) {
        userCrudService.deleteUser(requestUser.toUser());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Value(staticConstructor = "of")
    public static class RequestUser {

        @Email(message = "Email format is incorrect")
        @NotNull(message = "Email cannot be null")
        @Size(min = 2, message = "Email not be less than two characters")
        String email;

        @NotNull(message = "Username cannot be null")
        @Size(min = 2, message = "Email not be less than two characters")
        String username;

        @NotNull(message = "Password cannot be null")
        @Size(min = 8, max = 16, message = "Password must be equal or grater than 8 characters and less than 16 characters")
        String password;

        public User toUser() {
            return User.create(email, username, password);
        }

    }

    @Value(staticConstructor = "of")
    public static class ResponseUser {

        String email;
        String username;
        LocalDateTime createdAt;

        public static ResponseUser convert(User user) {
            return ResponseUser.of(user.getEmail(), user.getUsername(), user.getCreatedAt());
        }

    }

}
