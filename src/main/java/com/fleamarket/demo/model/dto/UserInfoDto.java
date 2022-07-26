package com.fleamarket.demo.model.dto;

import java.util.List;

import com.fleamarket.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserInfoDto {
    public String username;
    public String nickname;
    public String city;
    public List<ResponseItemDto> items;

    public UserInfoDto(User user, List<ResponseItemDto> items) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.city = user.getCity();
        this.items = items;
    }

}
