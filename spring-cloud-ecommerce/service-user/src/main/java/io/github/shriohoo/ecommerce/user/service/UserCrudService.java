package io.github.shriohoo.ecommerce.user.service;

import io.github.shriohoo.ecommerce.user.domain.User;

public interface UserCrudService {

    User createUser(User user);

    void deleteUser(User user);

}
