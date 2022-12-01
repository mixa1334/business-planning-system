package org.economics.planningsystem.controller.employee;

import org.economics.planningsystem.dto.employee.request.ChangeLoginRequest;
import org.economics.planningsystem.dto.employee.request.ChangePasswordRequest;
import org.economics.planningsystem.dto.employee.request.ChangeUserInfoRequest;
import org.economics.planningsystem.dto.employee.response.ChangeLoginResponse;
import org.economics.planningsystem.dto.employee.response.GetUserInfoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<GetUserInfoResponse> getUserInfo(@PathVariable Long id) {
        // TODO: 12/1/2022 get info from BD by user ID;
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUserInfo(@PathVariable Long id, @RequestBody ChangeUserInfoRequest changeUserInfoRequest) {
        // TODO: 12/1/2022 update user info by user id
        return null;
    }

    @PutMapping("/{id}/login")
    public ResponseEntity<ChangeLoginResponse> updateUserLogin(@PathVariable Long id, @RequestBody ChangeLoginRequest changeLoginRequest) {
        // TODO: 12/1/2022 update user login (check if login exists) and generate+return jwt token (using JwtProvider class)
        return null;
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<HttpStatus> updateUserPassword(@PathVariable Long id, @RequestBody ChangePasswordRequest changePasswordRequest) {
        // TODO: 12/1/2022 update user password by user id (need to encrypt by PasswordEncrypter)
        return null;
    }
}
