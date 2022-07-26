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
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {

            // 오류 핸들링으로 메인 페이지에 값이 없을 때 오류가 나지 않게 할 것이다.
            throw new MainItemException("유저를 찾아올 수 없습니다");
        }
        for (User user : users) {

            List<Item> itemList = user.getItemList();
            if (itemList.isEmpty()) {
                throw new MainItemException("아이템을 가져올 수 없습니다");
            }
            for (Item item : itemList) {
                MainResponseDto mainResponseDto = new MainResponseDto(user, item);
                mainItems.add(mainResponseDto);
            }
        }
        return mainItems;
    }

    public ResponseNickNameDto getNickName(UserDetailsImpl userDetails) {
        String nickname = userDetails.getUser().getNickname();
        return new ResponseNickNameDto(nickname);
    }
}
