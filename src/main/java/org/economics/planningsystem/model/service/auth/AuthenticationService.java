package org.economics.planningsystem.model.service.auth;

import org.economics.planningsystem.dto.auth.request.LoginRequest;
import org.economics.planningsystem.dto.auth.request.SignupRequest;
import org.economics.planningsystem.dto.auth.response.SignupResponse;
import org.economics.planningsystem.dto.auth.response.UserDetailsResponse;

public interface AuthenticationService {
    UserDetailsResponse login(LoginRequest loginRequest);

    SignupResponse signup(SignupRequest signupRequest);

    UserDetailsResponse getDetailsAboutUser(Long userId);
}
