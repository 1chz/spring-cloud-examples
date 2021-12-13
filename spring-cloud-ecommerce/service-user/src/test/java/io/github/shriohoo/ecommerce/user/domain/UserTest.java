package io.github.shriohoo.ecommerce.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import io.github.shriohoo.ecommerce.user.adapter.web.UserController.RequestUser;
import java.util.stream.Stream;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory()
            .getValidator();
    }

    @MethodSource
    @ParameterizedTest
    void createValidate(RequestUser requestUser) throws Exception {
        assertThat(validator.validate(requestUser)).isNotEmpty();
    }

    private static Stream<Arguments> createValidate() {
        return Stream.of(
            Arguments.of(RequestUser.of("s", "username", "password")),
            Arguments.of(RequestUser.of("ssa", "username", "password")),
            Arguments.of(RequestUser.of(null, "username", "password")),
            Arguments.of(RequestUser.of("ss@a.com", "username", "Is password between 8 and 16 characters?")),
            Arguments.of(RequestUser.of("ss@a.com", null, "password")),
            Arguments.of(RequestUser.of("ss@a.com", "username", null))
        );
    }

}
