package com.fleamarket.demo.service;

import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.ResultResponseDto;
import com.fleamarket.demo.model.dto.UserDto;
import com.fleamarket.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



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

    public ResultResponseDto registerUser(UserDto requestDto){
        String username =requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String city = requestDto.getCity();
        String enPassord = passwordEncoder.encode(requestDto.getPw());

        UserDto user = new UserDto(username, city, nickname, enPassord);
        User user1 =new User(user);
        userRepository.save(user1);
    }

    public ResultResponseDto duplicateUsername(String username) {
        return new ResultResponseDto(userRepository.existsByUsername(username));
    }

    public ResultResponseDto duplicatecNickname(String nickname) {
        return new ResultResponseDto(userRepository.existsByNickname(nickname));
    }

}
