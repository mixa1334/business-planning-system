package org.economics.planningsystem.model.service.auth;

import org.economics.planningsystem.dto.auth.request.LoginRequest;
import org.economics.planningsystem.dto.auth.request.SignupRequest;
import org.economics.planningsystem.dto.auth.response.SignupResponse;
import org.economics.planningsystem.dto.auth.response.UserDetailsResponse;

public interface AuthService {
    UserDetailsResponse login(LoginRequest loginRequest);

    SignupResponse signup(SignupRequest signupRequest);

    UserDetailsResponse getDetailsAboutUser();

    boolean isOwnerOfTask(Long taskId, Long profileId);
}
