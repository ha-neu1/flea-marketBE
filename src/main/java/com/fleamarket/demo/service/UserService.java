package com.fleamarket.demo.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.LoginRequestDto;
import com.fleamarket.demo.model.dto.ResultResponseDto;
import com.fleamarket.demo.model.dto.UserDto;
import com.fleamarket.demo.model.dto.UserInfoDto;
import com.fleamarket.demo.repository.ItemRepository;
import com.fleamarket.demo.repository.UserRepository;
import com.fleamarket.demo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public HashMap<String, String> duplicateUsername(String username) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        boolean isusername = userRepository.existsByUsername(username);
        if(isusername){
            throw new IllegalArgumentException("아이디가 존재합니다.");
        }
        return (HashMap<String, String>) mapper.readValue(username, Map.class);
    }

    public HashMap<String, String> duplicatecNickname(String nickname) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        boolean isnickname = userRepository.existsByNickname(nickname);
        if(isnickname){
            throw new IllegalArgumentException("닉네임이 존재합니다.");
        }
        return (HashMap<String, String>) mapper.readValue(nickname, Map.class);
    }

    public UserDto myinfo(UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("내 정보가 없습니다."));
        return new UserDto(user.getUsername(), user.getPw(), user.getNickname(), user.getCity());
    }

    public UserInfoDto myPost(UserDetailsImpl userDetails) {
        List<Item> itemList =
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();

        for(){
            UserInfoDto userInfoDto
        }
    }

    public Boolean login(LoginRequestDto loginRequestDto){
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElse(null);
        if (user != null) {
            if (!passwordEncoder.matches(loginRequestDto.getPw(), user.getPw())) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}