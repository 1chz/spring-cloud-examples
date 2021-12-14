package io.github.shriohoo.ecommerce.user.service;

import io.github.shriohoo.ecommerce.user.domain.User;
import java.util.List;

public interface UserCrudService {

    User createUser(User user);

    List<User> findAllUser();

    User findUser(Long id);

    void deleteUser(User user);

}
