package com.fleamarket.demo.service.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    public String username;
    public String pw;
    public String nickname;
    public String city;

}
