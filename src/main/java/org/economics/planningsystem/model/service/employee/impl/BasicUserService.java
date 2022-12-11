package org.economics.planningsystem.model.service.employee.impl;

import org.economics.planningsystem.dto.employee.request.ChangeLoginRequest;
import org.economics.planningsystem.dto.employee.request.ChangePasswordRequest;
import org.economics.planningsystem.dto.employee.request.ChangeUserInfoRequest;
import org.economics.planningsystem.dto.employee.response.ChangeLoginResponse;
import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.repository.employee.UserRepository;
import org.economics.planningsystem.model.service.employee.UserService;
import org.economics.planningsystem.security.jwt.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicUserService implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BasicUserService(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserInfo(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public void update(Long userId, ChangeUserInfoRequest request) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setAge(request.getAge());
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
    }

    @Override
    public ChangeLoginResponse updateLogin(Long userId, ChangeLoginRequest request) {
        String newLogin = request.getNewLogin();
        if (userRepository.existsByLogin(newLogin)) {
            throw new IllegalArgumentException("user already exists");
        }
        User user = userRepository.findById(userId).orElseThrow();
        user.setLogin(newLogin);
        userRepository.save(user);
        String token = jwtProvider.generateToken(newLogin);
        ChangeLoginResponse response = new ChangeLoginResponse();
        response.setToken(token);
        return response;
    }

    @Override
    public void updatePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId).orElseThrow();
        String rawPassword = request.getNewPassword();
        String newPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
