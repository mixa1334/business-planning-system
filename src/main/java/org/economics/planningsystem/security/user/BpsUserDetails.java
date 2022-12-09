package org.economics.planningsystem.security.user;

import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.employee.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class BpsUserDetails implements UserDetails {
    private final Long userId;
    private final Long statisticsId;
    private final String login;
    private final String password;
    private final Long profileId;
    private final Collection<? extends GrantedAuthority> authorities;

    public BpsUserDetails(Long userId, Long statisticsId, String login, String password, Long profileId, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.statisticsId = statisticsId;
        this.login = login;
        this.password = password;
        this.profileId = profileId;
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
        return login;
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

    public Long getUserId() {
        return userId;
    }

    public Long getStatisticsId() {
        return statisticsId;
    }

    public static UserDetails build(User user) {
        Long statisticsId = user.getStatistics().getId();
        EmployeeProfile profile = user.getProfile();
        if (profile != null) {
            Collection<? extends GrantedAuthority> authorities = profile.getRole().getAuthorities()
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();
            return new BpsUserDetails(
                    user.getId(),
                    statisticsId,
                    user.getLogin(),
                    user.getPassword(),
                    profile.getId(),
                    authorities
            );
        } else {
            return new BpsUserDetails(
                    user.getId(),
                    statisticsId,
                    user.getLogin(),
                    user.getPassword(),
                    null,
                    Set.of(new SimpleGrantedAuthority("USER"))
            );
        }
    }
}
