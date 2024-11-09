package io.sparta.auth.api;

import io.sparta.auth.api.dto.SignUpRequest;
import io.sparta.auth.api.dto.UserResponse;
import io.sparta.auth.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        UserResponse userResponse = userService.signUp(signUpRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

}
