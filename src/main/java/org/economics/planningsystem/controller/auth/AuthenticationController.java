package org.economics.planningsystem.controller.auth;

import org.economics.planningsystem.dto.auth.request.GetUserDetailsRequest;
import org.economics.planningsystem.dto.auth.request.LoginRequest;
import org.economics.planningsystem.dto.auth.response.LoginResponse;
import org.economics.planningsystem.dto.auth.request.SignupRequest;
import org.economics.planningsystem.dto.auth.response.SignupResponse;
import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.repository.employee.EmployeeProfileRepository;
import org.economics.planningsystem.model.service.employee.UserService;
import org.economics.planningsystem.model.service.organization.OrganizationService;
import org.economics.planningsystem.security.jwt.provider.JwtProvider;
import org.economics.planningsystem.security.user.BpsUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final OrganizationService organizationService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtProvider jwtProvider
            , OrganizationService organizationService, UserDetailsService userDetailsService
            , UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.organizationService = organizationService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String login = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        String token = jwtProvider.generateToken(login);
        BpsUserDetails userDetails = (BpsUserDetails) authentication.getPrincipal();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUserId(userDetails.getUserId());
        loginResponse.setStatisticsId(userDetails.getStatisticsId());
        loginResponse.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        Long profileId = userDetails.getProfileId();
        if (profileId != null) {
            loginResponse.setOrganizationId(organizationService.findOrganizationIdByProfileId(userDetails.getProfileId()));
            loginResponse.setProfileId(profileId);
        }
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        userService.create(signupRequest);
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setMessage("success!");
        return ResponseEntity.ok(signupResponse);
    }

    @PostMapping("/user_info")
    public ResponseEntity<LoginResponse> getUserDetails(@RequestBody GetUserDetailsRequest userDetailsRequest) {
        Long userId = userDetailsRequest.getUserId();
        User user = userService.getUserInfo(userId);
        String login = user.getLogin();
        BpsUserDetails userDetails = (BpsUserDetails) userDetailsService.loadUserByUsername(login);
        String token = jwtProvider.generateToken(login);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUserId(userDetails.getUserId());
        loginResponse.setStatisticsId(userDetails.getStatisticsId());
        loginResponse.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        Long profileId = userDetails.getProfileId();
        if (profileId != null) {
            loginResponse.setOrganizationId(organizationService.findOrganizationIdByProfileId(userDetails.getProfileId()));
            loginResponse.setProfileId(profileId);
        }
        return ResponseEntity.ok(loginResponse);
    }
}
