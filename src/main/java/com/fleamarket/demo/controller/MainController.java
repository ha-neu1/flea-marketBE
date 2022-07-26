package com.fleamarket.demo.controller;

import com.fleamarket.demo.model.dto.MainResponseDto;
import com.fleamarket.demo.model.dto.ResponseNickNameDto;
import com.fleamarket.demo.security.UserDetailsImpl;
import com.fleamarket.demo.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/main")
    public ResponseEntity<List<MainResponseDto>> showMain() {
        List<MainResponseDto> items = mainService.getMainItem();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/main/nickname")
    public ResponseEntity<ResponseNickNameDto> show(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ResponseNickNameDto nickName = mainService.getNickName(userDetails);
        return ResponseEntity.ok().body(nickName);
    }

}
