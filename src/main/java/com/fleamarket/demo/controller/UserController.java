package com.fleamarket.demo.controller;


import com.fleamarket.demo.service.model.dto.ResultResponseDto;
import com.fleamarket.demo.service.model.dto.UserDto;
import com.fleamarket.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultResponseDto duplicateUsername(@RequestBody String username) {
        System.out.println(username);
        return userService.duplicateUsername(username);
    }

    //닉네임 중복 확인
    @PostMapping("/user/join/nickname")
    public ResultResponseDto duplicateNickname(@RequestBody String nickname) {
        return userService.duplicatecNickname(nickname);
    }

}

