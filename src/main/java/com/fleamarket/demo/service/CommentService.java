package com.fleamarket.demo.service;

import com.fleamarket.demo.model.Comment;
import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import com.fleamarket.demo.model.dto.CommentDto;
import com.fleamarket.demo.model.dto.CommentRequestDto;
import com.fleamarket.demo.repository.CommentRepository;
import com.fleamarket.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;
    public CommentDto createComment(CommentRequestDto commentDto, User user) {
        Item item = itemRepository.findByItemName(commentDto.getItemName()).orElseThrow(
                () -> new IllegalArgumentException("아이템이 존재하지 않습니다")
        );
        Comment comment = new Comment(commentDto.getComment(), user.getNickname());
        comment.setRelationItem(item);
        commentRepository.save(comment);

        return new CommentDto(comment);
    }
}
