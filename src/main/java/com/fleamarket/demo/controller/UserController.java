package com.fleamarket.demo.controller;

import com.fleamarket.demo.model.dto.ResultResponseDto;
import com.fleamarket.demo.model.dto.UserDto;
import com.fleamarket.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/user/join")
    public ResultResponseDto registerUser(@RequestBody UserDto requestDto) {
        userService.registerUser(requestDto);
        return new ResultResponseDto(true);
    }

    //아이디 중복 확인
    @PostMapping("/user/join/username")
    public ResultResponseDto duplicateUsername(@PathVariable("username") String username) {
        return userService.duplicateUsername(username);
    }

    //닉네임 중복 확인
    @PostMapping("/user/join/nickname")
    public ResultResponseDto duplicateNickname(@PathVariable("nickname") String nickname) {
        return userService.duplicatecNickname(nickname);
    }
}