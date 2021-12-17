package io.github.shriohoo.ecommerce.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class RequestLogin {

    @Email
    @NotNull(message = "Email must not be null;")
    @Size(min = 2, message = "Email must not be less than 2 characters")
    String email;

    @NotNull(message = "Password must not be null;")
    @Size(min = 8, message = "Password must be equals or grater than 8 characters")
    String password;

}
