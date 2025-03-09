package com.example.jwtfilter3.auth.service;

import com.example.jwtfilter3.auth.dto.SigninRequest;
import com.example.jwtfilter3.auth.dto.SigninResponse;
import com.example.jwtfilter3.auth.dto.SignupRequest;
import com.example.jwtfilter3.auth.dto.SignupResponse;
import com.example.jwtfilter3.config.JwtUtil;
import com.example.jwtfilter3.user.dto.UserResponse;
import com.example.jwtfilter3.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponse signup(SignupRequest request) {
        UserResponse saveResult = userService.save(request.getEmail());

        String bearerJwt = jwtUtil.createToken(saveResult.getId(), saveResult.getEmail());

        return new SignupResponse(bearerJwt, saveResult.getId());
    }

    @Transactional
    public SigninResponse signin(SigninRequest request) {
        UserResponse userResult = userService.findByEmail(request.getEmail());

        String bearerJwt = jwtUtil.createToken(userResult.getId(), request.getEmail());

        return new SigninResponse(bearerJwt);
    }
}
