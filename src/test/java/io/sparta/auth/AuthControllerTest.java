package io.sparta.auth;

import io.sparta.auth.api.AuthController;
import io.sparta.auth.api.dto.SignUpRequest;
import io.sparta.auth.api.dto.UserResponse;
import io.sparta.auth.application.UserService;
import io.sparta.auth.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }


    @DisplayName("회원가입 성공")
    @Test
    void signUpSuccess() throws Exception {

        // Given
        String username = "test1";
        String password = "password";
        String nickname = "testtttttt";
        SignUpRequest signUpRequest = new SignUpRequest(username, password, nickname);
        UserResponse userResponse = new UserResponse(username, nickname, Role.USER);

        doReturn(userResponse).when(userService).signUp(any(SignUpRequest.class));


        // JSON 형식의 요청 데이터
        String requestContent = """
            {
                "username": "test1",
                "password": "password",
                "nickname": "testtttttt"
            }
            """;


        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent)
                        .characterEncoding("UTF-8")
        );

        // then
        MvcResult mvcResult = resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("test1"))
                .andExpect(jsonPath("$.nickname").value("testtttttt"))
                .andExpect(jsonPath("$.role").value("USER"))
                .andReturn();


        // 테스트 결과 출력
        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("Response Content: " + responseContent);

    }
}
