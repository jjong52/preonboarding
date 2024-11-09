package io.sparta.auth.api.dto;

import io.sparta.auth.domain.Role;
import io.sparta.auth.domain.User;

public record UserResponse(
        String username,
        String nickname,
        Role role
) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getUsername(), user.getNickname(), user.getRole());
    }
}
