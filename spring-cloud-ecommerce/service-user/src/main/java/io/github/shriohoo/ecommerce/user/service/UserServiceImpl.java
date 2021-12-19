package io.github.shriohoo.ecommerce.user.service;

import io.github.shriohoo.ecommerce.user.adapter.persistence.UserCurdRepository;
import io.github.shriohoo.ecommerce.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserCurdRepository userCurdRepository;

    @Override
    public User save(User user) {
        return userCurdRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userCurdRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userCurdRepository.findById(id)
            .orElseThrow();
    }

    @Override
    public void delete(User user) {
        userCurdRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userCurdRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(),
            true, true, true, true,
            new ArrayList<>()
        );
    }

}
