package com.fleamarket.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fleamarket.demo.model.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String nickname;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public Comment(CommentDto commentDto) {
        this.nickname = commentDto.getNickname();
        this.comment = commentDto.getComment();
    }

    public Comment(String comment, String nickname) {
        this.nickname = nickname;
        this.comment = comment;
    }

    public void setRelationItem(Item item) {
        this.item = item;
        item.getComments().add(this);
    }
}
