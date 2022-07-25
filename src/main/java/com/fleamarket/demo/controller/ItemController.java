package com.fleamarket.demo.controller;


import com.fleamarket.demo.model.dto.ItemDto;
import com.fleamarket.demo.security.UserDetailsImpl;
import com.fleamarket.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ResponseBody
    @PostMapping("/item/update")
    public String saveItem(
            @ModelAttribute ItemDto itemDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        ItemDto dto = itemService.saveImage(itemDto, itemDto.getImage(), userDetails.getUsername());
        return "아이템 저장이 성공했습니다.";
    }

    @ResponseBody
    @GetMapping(value = "/item/show/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public  ResponseEntity<Resource> showImage(@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return ResponseEntity.ok().body(itemService.showImage(userDetails.getUsername()));
    }
}
