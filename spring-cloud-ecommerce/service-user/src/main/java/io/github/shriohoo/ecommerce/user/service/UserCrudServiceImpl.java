package io.github.shriohoo.ecommerce.user.service;

import io.github.shriohoo.ecommerce.user.adapter.persistence.UserCurdRepository;
import io.github.shriohoo.ecommerce.user.adapter.persistence.UserEntity;
import io.github.shriohoo.ecommerce.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCrudServiceImpl implements UserCrudService {

    private final UserCurdRepository userCurdRepository;

    @Override
    public User createUser(User user) {
        UserEntity savedUser = userCurdRepository.save(user);
        return savedUser.toUser();
    }

    @Override
    public List<User> findAllUser() {
        return userCurdRepository.findAll()
            .stream()
            .map(UserEntity::toUser)
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public User findUser(Long id) {
        UserEntity findUser = userCurdRepository.findById(id);
        return findUser.toUser();
    }

    @Override
    public void deleteUser(User user) {
        userCurdRepository.delete(user);
    }

}
