package org.economics.planningsystem.security;

import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.employee.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUserDetails implements UserDetails {
    private final Long profileId;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public SecurityUserDetails(Long profileId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.profileId = profileId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getProfileId() {
        return profileId;
    }

    public static UserDetails build(User user) {
        EmployeeProfile profile = user.getProfile();
        Collection<? extends GrantedAuthority> authorities = profile.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new SecurityUserDetails(
                profile.getId(),
                user.getLogin(),
                user.getPassword(),
                authorities
        );
    }
}
