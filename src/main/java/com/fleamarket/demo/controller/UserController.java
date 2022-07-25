package com.fleamarket.demo.controller;


import com.fleamarket.demo.jwt.JwtTokenProvider;
import com.fleamarket.demo.model.dto.LoginRequestDto;
import com.fleamarket.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto,
                        HttpServletResponse response) {
        if (this.userService.login(loginRequestDto)) {
            String token = this.jwtTokenProvider.createToken(loginRequestDto.getUsername());
            System.out.println("token = " + token);

            // JWT 전송
            response.setHeader("Authorization", token);

            Cookie cookie = new Cookie("JWT", token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);

            return "로그인이 성공했습니다.";
        } else {
            return "닉네임 또는 패스워드를 확인해주세요";
        }
    }
//    @PostMapping({"/user/signup"})
//    public String registerUser(@RequestBody @Valid SignupRequestDto requestDto) {
//        String res = this.userService.registerUser(requestDto);
//        return res.equals("") ? "회원가입 성공" : res;
//    }

}
