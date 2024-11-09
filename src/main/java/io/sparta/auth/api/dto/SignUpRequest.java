package io.sparta.auth.api.dto;

public record SignUpRequest(
        String username,
        String password,
        String nickname
) {
}
