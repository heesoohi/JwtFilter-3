package com.example.jwtfilter3.user.service;

import com.example.jwtfilter3.auth.dto.AuthUser;
import com.example.jwtfilter3.user.dto.UserResponse;
import com.example.jwtfilter3.user.dto.UserUpdateRequest;
import com.example.jwtfilter3.user.entity.User;
import com.example.jwtfilter3.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse save(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User(email);
        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(), savedUser.getEmail());
    }

    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("Email not found")
        );

        return new UserResponse(user.getId(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getEmail()))
                .toList();
    }

    @Transactional
    public void update(AuthUser authUser, UserUpdateRequest request) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(
                () -> new IllegalStateException("User not found")
        );

        user.update(request.getEmail());

        User savedUser = userRepository.save(user);
    }
}
