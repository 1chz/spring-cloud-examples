package io.github.shriohoo.ecommerce.user.adapter.persistence;

import io.github.shriohoo.ecommerce.user.domain.User;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCurdRepositoryImpl implements UserCurdRepository {

    private final UserCrudJpaRepository jpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity save(User user) {
        UserEntity userEntity = UserEntity.convert(user);
        userEntity.encryptPassword(passwordEncoder);
        return jpaRepository.save(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {
        return jpaRepository.findById(id)
            .orElseThrow(NoSuchElementException::new);
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
