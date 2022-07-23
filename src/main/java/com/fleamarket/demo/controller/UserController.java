package com.fleamarket.demo.controller;

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
    public void registerUser(@RequestBody UserDto requestDto) {
        userService.registerUser(requestDto);
    }

    //아이디 중복 확인
    @PostMapping("/user/join/username")
    public ResultResponseDto duplicateUsername(@PathVariable("username") String username) {
        System.out.println("idCheck input username : "+username);
        System.out.println("idCheck result : " + userService.duplicateUsername(username).isResult());
        System.out.println("idCheck result reverse : " + userService.duplicateUsername(username));
        return userService.duplicateUsername(username);
    }

    //닉네임 중복 확인
    @PostMapping("/user/join/nickname")
    public ResultResponseDto duplicateNickname(@PathVariable("nickname") String nickname) {
        System.out.println("nicknameCheck input nickname : " + nickname);
        System.out.println("nicknameCheck result : " + userService.duplicatecNickname(nickname).isResult());
        return userService.duplicatecNickname(nickname);
    }
}