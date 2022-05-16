package ru.kronx.backend.ecomoney.secure;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kronx.backend.ecomoney.entity.User;
import ru.kronx.backend.ecomoney.repository.UserRepository;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {
    private final  UserRepository userRepository;
    public UserDetailServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user does not exist"));
        return SecurityUser.fromUser(user);
    }
}
