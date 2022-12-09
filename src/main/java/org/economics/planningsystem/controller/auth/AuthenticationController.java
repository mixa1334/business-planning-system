package org.economics.planningsystem.controller.auth;

import org.economics.planningsystem.dto.auth.request.GetUserDetailsRequest;
import org.economics.planningsystem.dto.auth.request.LoginRequest;
import org.economics.planningsystem.dto.auth.response.UserDetailsResponse;
import org.economics.planningsystem.dto.auth.request.SignupRequest;
import org.economics.planningsystem.dto.auth.response.SignupResponse;
import org.economics.planningsystem.model.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetailsResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetailsResponse response = authenticationService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        SignupResponse response = authenticationService.signup(signupRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user_details")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@RequestBody GetUserDetailsRequest userDetailsRequest) {
        Long userId = userDetailsRequest.getUserId();
        UserDetailsResponse response = authenticationService.getDetailsAboutUser(userId);
        return ResponseEntity.ok(response);
    }
}
