package com.jpa.blog.user;

import com.jpa.blog.domain.user.SignUpDTO;
import com.jpa.blog.domain.user.User;
import com.jpa.blog.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("SignUp Test")
public class SignUpTest {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpTest(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    void createUser() {
        //given
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .userId("")
                .userNickname("")
                .userPassword("")
                .build();

        //when
        SignUpDTO encodedUser = SignUpDTO.builder()
                .userId(signUpDTO.getUserId())
                .userNickname(signUpDTO.getUserNickname())
                .userPassword(passwordEncoder.encode(signUpDTO.getUserPassword()))
                .build();
        User signedUpUser = userService.insertUser(encodedUser);

        //then
        assertAll(
                "SignUp Test",
                () -> assertEquals(signUpDTO.getUserId(), signedUpUser.getUserId()),
                () -> assertEquals(signUpDTO.getUserNickname(), signedUpUser.getUserNickname()),
                () -> assertTrue(passwordEncoder.matches(signUpDTO.getUserPassword(), signedUpUser.getUserPassword()))
        );
    }
}
