package io.github.shriohoo.ecommerce.user.service;

import io.github.shriohoo.ecommerce.user.domain.User;
import java.util.List;

public interface UserCrudService {

    User save(User user);

    List<User> findAll();

    User findById(Long id);

    void delete(User user);

}
