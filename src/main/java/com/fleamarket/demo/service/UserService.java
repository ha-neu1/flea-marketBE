package com.fleamarket.demo.service;


import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.ResultResponseDto;
import com.fleamarket.demo.model.dto.UserDto;
import com.fleamarket.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDto requestDto){
        String username =requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String city = requestDto.getCity();
        String enPassord = passwordEncoder.encode(requestDto.getPw());

        if(!username.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")) {
            throw new IllegalArgumentException("email 형식에 맞지 않습니다.");
        }
        if (!nickname.matches("^[a-zA-Z0-9]{3,15}$")) {
            throw new IllegalArgumentException("nickname 조건이 맞지 않습니다.");
        }

        UserDto user = new UserDto(username,nickname,city,enPassord);
        User user1 =new User(user);
        userRepository.save(user1);



    }

    public HashMap<String, String> duplicateUsername(String username) {
        boolean isusername = userRepository.existsByUsername(username);
        if(isusername){
            throw new IllegalArgumentException("아이디가 존재합니다.");
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        return map;
    }

    public ResultResponseDto duplicatecNickname(String nickname) {
        boolean isnickname = userRepository.existsByNickname(nickname);
        if(isnickname){
            throw new IllegalArgumentException("닉네임이 존재합니다.");
        }
        return new ResultResponseDto(nickname);
    }


}
