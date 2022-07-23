package com.fleamarket.demo.controller;


import com.fleamarket.demo.model.dto.ItemDto;
import com.fleamarket.demo.security.UserDetailsImpl;
import com.fleamarket.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ResponseBody
    @PostMapping("/item/update")
    public String saveItem(
            @RequestParam(value = "item_detail") String itemDetail,
            @RequestParam(value = "item_price") int itemPrice,
            @RequestParam(value = "item_name") String itemName,
            @RequestParam(value = "image") MultipartFile multipartFile,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        ItemDto itemDto = new ItemDto(itemName, itemPrice, itemDetail);
        ItemDto dto = itemService.saveImage(itemDto, multipartFile, userDetails.getUsername());

        return "아이템 저장이 성공했습니다.";
    }
}
