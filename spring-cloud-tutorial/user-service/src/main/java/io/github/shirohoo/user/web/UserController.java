package io.github.shirohoo.user.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;

    @GetMapping("/")
    public String index() {
        return "This is User Service:" + env.getProperty("server.port");
    }

}
