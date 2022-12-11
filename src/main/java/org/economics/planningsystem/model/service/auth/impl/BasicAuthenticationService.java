package org.economics.planningsystem.model.service.auth.impl;

import org.economics.planningsystem.dto.auth.request.LoginRequest;
import org.economics.planningsystem.dto.auth.request.SignupRequest;
import org.economics.planningsystem.dto.auth.response.SignupResponse;
import org.economics.planningsystem.dto.auth.response.UserDetailsResponse;
import org.economics.planningsystem.model.entity.employee.EmployeeStatistics;
import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.repository.employee.UserRepository;
import org.economics.planningsystem.model.service.auth.AuthenticationService;
import org.economics.planningsystem.security.jwt.provider.JwtProvider;
import org.economics.planningsystem.security.user.BpsUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicAuthenticationService implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BasicAuthenticationService(AuthenticationManager authenticationManager, JwtProvider jwtProvider
            , UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetailsResponse login(LoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        BpsUserDetails userDetails = (BpsUserDetails) authentication.getPrincipal();
        return createUserDetailResponse(userDetails);
    }

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        String login = signupRequest.getLogin();
        if (userRepository.existsByLogin(login)){
            throw new IllegalArgumentException("user already exists");
        }
        String password = passwordEncoder.encode(signupRequest.getPassword());
        String name = signupRequest.getName();
        String surname = signupRequest.getSurname();
        Integer age = signupRequest.getAge();
        String phoneNumber = signupRequest.getPhoneNumber();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        user.setPhoneNumber(phoneNumber);
        EmployeeStatistics statistics = new EmployeeStatistics();
        statistics.setCompletedAfterDeadline(0L);
        statistics.setCompletedOnTime(0L);
        statistics.setCompletedTasks(0L);
        statistics.setEfficiency(0L);
        user.setStatistics(statistics);
        userRepository.save(user);
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setMessage("success!");
        return signupResponse;
    }

    @Override
    public UserDetailsResponse getDetailsAboutUser(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow();
        BpsUserDetails userDetails = (BpsUserDetails) BpsUserDetails.build(user);
        return createUserDetailResponse(userDetails);
    }

    private UserDetailsResponse createUserDetailResponse(BpsUserDetails userDetails) {
        String token = jwtProvider.generateToken(userDetails.getLogin());
        UserDetailsResponse loginResponse = new UserDetailsResponse();
        loginResponse.setToken(token);
        loginResponse.setUserId(userDetails.getUserId());
        loginResponse.setStatisticsId(userDetails.getStatisticsId());
        loginResponse.setRoles(userDetails.getRoles());
        Long profileId = userDetails.getProfileId();
        loginResponse.setProfileId(profileId);
        loginResponse.setOrganizationId(userDetails.getOrganizationId());
        return loginResponse;
    }
}
