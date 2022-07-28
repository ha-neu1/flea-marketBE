package com.fleamarket.demo.service;

import com.fleamarket.demo.Exception.MainItemException;
import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.MainResponseDto;
import com.fleamarket.demo.model.dto.ResponseNickNameDto;
import com.fleamarket.demo.repository.ItemRepository;
import com.fleamarket.demo.repository.UserRepository;
import com.fleamarket.demo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    public List<MainResponseDto> getMainItem() {
            List<MainResponseDto> mainItems = new ArrayList<>();
        try {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                List<Item> itemList = user.getItemList();
                for (Item item : itemList) {
                    MainResponseDto mainResponseDto = new MainResponseDto(user, item);
                    mainItems.add(mainResponseDto);
                }
            }

        } catch (Exception e) {
            throw new MainItemException("아이템을 찾아올 수 없습니다.");
        }
        return mainItems;
    }

    public ResponseNickNameDto getNickName(UserDetailsImpl userDetails) {
        String nickname = userDetails.getUser().getNickname();
        return new ResponseNickNameDto(nickname);
    }
}
