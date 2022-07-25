package com.fleamarket.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    public String itemName;
    public int itemPrice;
    public String itemDetail;
    public MultipartFile image;

}
