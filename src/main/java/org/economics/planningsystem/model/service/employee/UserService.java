package org.economics.planningsystem.model.service.employee;

import org.economics.planningsystem.dto.employee.request.ChangeLoginRequest;
import org.economics.planningsystem.dto.employee.request.ChangePasswordRequest;
import org.economics.planningsystem.dto.employee.request.ChangeUserInfoRequest;
import org.economics.planningsystem.dto.employee.response.ChangeLoginResponse;
import org.economics.planningsystem.model.entity.employee.User;

public interface UserService {
    User getUserInfo(Long userId);

    void update(Long userId, ChangeUserInfoRequest request);

    ChangeLoginResponse updateLogin(Long userId, ChangeLoginRequest request);

    void updatePassword(Long userId, ChangePasswordRequest request);
}
