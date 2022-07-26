package com.fleamarket.demo.controller;


import com.fleamarket.demo.model.dto.CommentDto;
import com.fleamarket.demo.model.dto.CommentRequestDto;
import com.fleamarket.demo.security.UserDetailsImpl;
import com.fleamarket.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public String createComment(@RequestBody CommentRequestDto CommentRequestDto,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentDto comment = commentService.createComment(CommentRequestDto, userDetails.getUser());
        return "코멘트가 잘 저장되었습니다.";
    }
}
