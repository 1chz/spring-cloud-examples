package io.github.shriohoo.ecommerce.user.adapter.persistence;

import io.github.shriohoo.ecommerce.user.domain.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCurdRepositoryImpl implements UserCurdRepository {

    private final UserJpaRepository jpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.convert(user);
        userEntity.encryptPassword(passwordEncoder);
        return jpaRepository.save(userEntity)
            .toUser();
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id)
            .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
            .map(UserEntity::toUser);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll()
            .stream()
            .map(UserEntity::toUser)
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void delete(User user) {
        jpaRepository.delete(UserEntity.convert(user));
    }

}
