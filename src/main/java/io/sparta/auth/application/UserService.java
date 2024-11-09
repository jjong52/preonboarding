package io.sparta.auth.application;

import io.sparta.auth.api.dto.SignUpRequest;
import io.sparta.auth.api.dto.UserResponse;
import io.sparta.auth.domain.Role;
import io.sparta.auth.domain.User;
import io.sparta.auth.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserResponse signUp(SignUpRequest signUpRequest) {
        String username = signUpRequest.username();
        String password = signUpRequest.password();

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User user = User.of(username, encodedPassword, signUpRequest.nickname(), Role.USER);

        userRepository.save(user);

        return UserResponse.from(user);
    }
}
