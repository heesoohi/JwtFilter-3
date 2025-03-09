package com.example.jwtfilter3.user.controller;

import com.example.jwtfilter3.auth.annotation.Auth;
import com.example.jwtfilter3.auth.dto.AuthUser;
import com.example.jwtfilter3.user.dto.UserResponse;
import com.example.jwtfilter3.user.dto.UserUpdateRequest;
import com.example.jwtfilter3.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAll(@Auth AuthUser authUser) {
        return ResponseEntity.ok(userService.getAll());
    }

    // 본인 정보 수정
    @PutMapping("/users")
    public void update(@Auth AuthUser authUser, @RequestBody UserUpdateRequest request) {
        userService.update(authUser, request);
    }
}
