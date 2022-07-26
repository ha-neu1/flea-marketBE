package com.fleamarket.demo.model.dto;


import com.fleamarket.demo.model.Item;

import java.util.List;



import com.fleamarket.demo.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserInfoDto {
    public String username;
    public String pw;
    public String nickname;
    public String city;

    public List<Item> itemList;

    public List<Item> itemLists;


}
