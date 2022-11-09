package org.economics.planningsystem.security.user;

import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.repository.employee.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BpsUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public BpsUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository
                .findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        return BpsUserDetails.build(user);
    }
}
