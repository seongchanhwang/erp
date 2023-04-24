package io.galchung.erp.web.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception {

        LoginForm loginForm = new LoginForm();
        String httpBodyData = objectMapper.writeValueAsString(loginForm);


    }


}