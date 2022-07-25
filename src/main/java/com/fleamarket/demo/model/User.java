package com.fleamarket.demo.model;

import com.fleamarket.demo.model.dto.UserDto;
import com.fleamarket.demo.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String pw;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String city;
    @OneToMany(mappedBy = "user")
    private List<Item> itemList = new ArrayList<>();
    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.pw = userDto.getPw();
        this.nickname = userDto.getNickname();
        this.city = userDto.getCity();
    }
}
