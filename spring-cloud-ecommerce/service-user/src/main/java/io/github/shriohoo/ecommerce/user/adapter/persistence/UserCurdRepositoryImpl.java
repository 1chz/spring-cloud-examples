package io.github.shriohoo.ecommerce.user.adapter.persistence;

import io.github.shriohoo.ecommerce.user.domain.User;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCurdRepositoryImpl implements UserCurdRepository {

    private final UserCrudJpaRepository jpaRepository;

    @Override
    public UserEntity save(User user) {
        return jpaRepository.save(UserEntity.convert(user));
    }

    @Override
    public UserEntity findByUsername(String username) {
        return jpaRepository.findByUsername(username)
            .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<UserEntity> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(User user) {
        jpaRepository.delete(UserEntity.convert(user));
    }

}
