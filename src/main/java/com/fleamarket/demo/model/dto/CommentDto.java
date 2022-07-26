package com.fleamarket.demo.model.dto;

import com.fleamarket.demo.model.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    public String comment;
    public String nickname;

    public CommentDto(Comment comment) {
        this.comment = comment.getComment();
        this.nickname = comment.getNickname();
    }
}
