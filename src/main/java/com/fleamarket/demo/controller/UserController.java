package com.fleamarket.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fleamarket.demo.jwt.JwtTokenProvider;
import com.fleamarket.demo.model.dto.LoginRequestDto;
import com.fleamarket.demo.model.dto.UserDto;
import com.fleamarket.demo.model.dto.UserInfoDto;
import com.fleamarket.demo.security.UserDetailsImpl;
import com.fleamarket.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    //회원가입
    @PostMapping("/user/join")
    public String registerUser(@RequestBody UserDto requestDto) {
        userService.registerUser(requestDto);
        return "회원 가입이 완료되었습니다";
    }

    //아이디 중복 확인
    @PostMapping("/user/join/username")
    public ResponseEntity<Map<String, String>> duplicateUsername(@RequestBody String username) throws JsonProcessingException {
        Map<String, String> stringMap = userService.duplicateUsername(username);
        return ResponseEntity.ok().body(stringMap);
    }

    //닉네임 중복 확인
    @PostMapping("/user/join/nickname")
    public ResponseEntity<Map<String, String>> duplicateNickname(@RequestBody String nickname) throws JsonProcessingException {
        Map<String, String> stringMap = userService.duplicatecNickname(nickname);
        return ResponseEntity.ok().body(stringMap);
    }

    //회원 정보 조회
    @GetMapping("/user/info")
    public ResponseEntity<UserDto> myinfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(userService.myinfo(userDetails));
    }

    //작성한 게시글 조회
    @GetMapping("/user/info")
    public ResponseEntity<UserInfoDto> myPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(userService.myPost(userDetails));
    }

    //로그인
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
