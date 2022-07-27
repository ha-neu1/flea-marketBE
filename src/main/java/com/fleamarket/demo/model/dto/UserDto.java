package com.fleamarket.demo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    public String username;
    public String pw;
    public String nickname;
    public String city;
}
