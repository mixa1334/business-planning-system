package org.economics.planningsystem.security;

import org.economics.planningsystem.model.repository.employee.UserRepository;
import org.economics.planningsystem.security.exception.BpsAccessDeniedHandler;
import org.economics.planningsystem.security.exception.BpsAuthenticationEntryPoint;
import org.economics.planningsystem.security.exception.ResponseExceptionWriter;
import org.economics.planningsystem.security.jwt.filter.JwtFilter;
import org.economics.planningsystem.security.jwt.provider.JwtProvider;
import org.economics.planningsystem.security.user.BpsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    @Value("${jwt.header}")
    private String authorizationHeaderName;
    @Value("${bps.security.password_encoder_strength}")
    private Integer passwordEncoderStrength;
    @Value("${bps.security.authentication_controller_path}")
    private String authenticationControllerPath;

    @Autowired
    public SecurityConfig(JwtProvider jwtProvider, UserRepository userRepository) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(authenticationControllerPath).permitAll()
                .antMatchers(authenticationControllerPath).permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling()
                .accessDeniedHandler(bpsAccessDeniedHandler())
                .authenticationEntryPoint(bpsAuthenticationEntryPoint());
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(bpsUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(passwordEncoderStrength);
    }

    @Bean
    public AuthenticationEntryPoint bpsAuthenticationEntryPoint() {
        return new BpsAuthenticationEntryPoint(responseExceptionWriter());
    }

    @Bean
    public AccessDeniedHandler bpsAccessDeniedHandler() {
        return new BpsAccessDeniedHandler(responseExceptionWriter());
    }

    @Bean
    public ResponseExceptionWriter responseExceptionWriter() {
        return new ResponseExceptionWriter();
    }

    @Bean
    public UserDetailsService bpsUserDetailsService() {
        return new BpsUserDetailsService(userRepository);
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtFilter(authorizationHeaderName, jwtProvider, bpsUserDetailsService());
    }
}
