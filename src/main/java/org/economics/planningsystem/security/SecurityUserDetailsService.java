package org.economics.planningsystem.security;

import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.repository.employee.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceServiceImpl")
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public SecurityUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository
                .findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        return SecurityUserDetails.build(user);
    }
}
