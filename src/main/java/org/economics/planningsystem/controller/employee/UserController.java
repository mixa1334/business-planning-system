package org.economics.planningsystem.controller.employee;

import org.economics.planningsystem.dto.employee.request.ChangeLoginRequest;
import org.economics.planningsystem.dto.employee.request.ChangePasswordRequest;
import org.economics.planningsystem.dto.employee.request.ChangeUserInfoRequest;
import org.economics.planningsystem.dto.employee.response.ChangeLoginResponse;
import org.economics.planningsystem.dto.employee.response.GetUserInfoResponse;
import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.service.employee.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserInfoResponse> getUserInfo(@PathVariable Long id) {
        User user = userService.getUserInfo(id);
        GetUserInfoResponse response = GetUserInfoResponse.build(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUserInfo(@PathVariable Long id, @Valid @RequestBody ChangeUserInfoRequest changeUserInfoRequest) {
        userService.update(id, changeUserInfoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/login")
    public ResponseEntity<ChangeLoginResponse> updateUserLogin(@PathVariable Long id, @Valid @RequestBody ChangeLoginRequest changeLoginRequest) {
        ChangeLoginResponse response = userService.updateLogin(id, changeLoginRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<HttpStatus> updateUserPassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.updatePassword(id, changePasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
