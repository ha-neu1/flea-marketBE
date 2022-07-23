package com.fleamarket.demo.controller;

import com.fleamarket.demo.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/join/username")
    public String registerUsername(@RequestBody UserDto userDto) {
        String reu = UserService.registerUsername(userDto);
        if (reu.equals("")) {
            return "유저 이름 확인";
        } else {
            return reu;
        }
    }

    @PostMapping("/user/join/nickname")
    public String registerNickname(@RequestBody UserDto userDto) {
        String ren = UserService.registerNickname(userDto);
        if (ren.equals("")) {
            return "닉네임 확인";
        } else {
            return ren;
        }
    }

    @PostMapping("/user/join")
    public String registerUser(@Valid @RequestBody UserDto requestDto) {
        String res = UserService.registerUser(requestDto);
        if (res.equals("")) {
            return "회원가입 성공";
        } else {
            return res;
        }
    }
}