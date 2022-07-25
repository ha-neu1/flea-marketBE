package com.fleamarket.demo.model;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public Comment(CommentDto commentDto) {
        this.comment = commentDto.getComment();
    }

    public void setRelationItem(Item item) {
        this.item = item;
        item.getComments().add(this);
    }
}
