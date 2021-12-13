package io.github.shriohoo.ecommerce.user.adapter.persistence;

import io.github.shriohoo.ecommerce.user.domain.User;
import java.util.List;

public interface UserCurdRepository {

    UserEntity save(User user);

    UserEntity findByUsername(String username);

    List<UserEntity> findAll();

    void delete(User user);

}
