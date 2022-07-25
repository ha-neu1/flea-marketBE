package com.fleamarket.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fleamarket.demo.model.dto.ResultResponseDto;
import com.fleamarket.demo.model.dto.UserDto;
import com.fleamarket.demo.repository.UserRepository;
import com.fleamarket.demo.security.UserDetailsImpl;
import com.fleamarket.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

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
}

