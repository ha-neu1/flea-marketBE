package com.fleamarket.demo.model.dto;

import com.fleamarket.demo.model.Comment;
import com.fleamarket.demo.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    public List<Comment> comments;
    public String itemName;
    public Resource image;
    public int itemPrice;
    public String itemDetail;

    public CommentResponseDto(Item item, List<Comment> comments, Resource image) {
        this.itemName = item.getItemName();
        this.itemDetail = item.getItemDetail();
        this.itemPrice = item.getItemPrice();
        this.comments = comments;
        this.image = image;
    }
}
