package org.economics.planningsystem.controller.auth;

import org.economics.planningsystem.dto.auth.request.LoginRequest;
import org.economics.planningsystem.dto.auth.response.LoginResponse;
import org.economics.planningsystem.dto.auth.request.SignupRequest;
import org.economics.planningsystem.model.repository.employee.EmployeeProfileRepository;
import org.economics.planningsystem.model.repository.employee.UserRepository;
import org.economics.planningsystem.model.service.organization.OrganizationService;
import org.economics.planningsystem.security.jwt.provider.JwtProvider;
import org.economics.planningsystem.security.user.BpsUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final OrganizationService organizationService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtProvider jwtProvider
            , EmployeeProfileRepository employeeProfileRepository, OrganizationService organizationService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.employeeProfileRepository = employeeProfileRepository;
        this.organizationService = organizationService;
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
        // TODO: 11/30/2022 if there is no profile
        loginResponse.setOrganizationId(organizationService.findOrganizationIdByProfileId(userDetails.getProfileId()));
        loginResponse.setProfileId(userDetails.getProfileId());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signup(@RequestBody SignupRequest signupRequest) {
        String login = signupRequest.getLogin();
        String password = signupRequest.getPassword();

        // TODO: 11/30/2022

        return null;

    }
}
