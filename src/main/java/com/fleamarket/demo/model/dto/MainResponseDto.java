package com.fleamarket.demo.model.dto;

import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MainResponseDto {
    public String userNickName;
    public String userCity;
    public Long itemId;
    public String image;
    public int itemPrice;

    public MainResponseDto(User user, Item item) {
        this.userNickName = user.getNickname();
        this.userCity = user.getCity();
        this.itemId = item.getId();
        this.image = item.getFile().getFileUrl();
        this.itemPrice = item.getItemPrice();
    }
}
