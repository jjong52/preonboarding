package io.sparta.auth.api.dto;

public record SignInRequest(
        String username,
        String password
) {
}
