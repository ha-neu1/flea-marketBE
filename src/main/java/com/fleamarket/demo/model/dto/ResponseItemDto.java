package com.fleamarket.demo.model.dto;


import com.fleamarket.demo.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResponseItemDto {
    public Long itemId;
    public String image;
    public String itemName;

    public ResponseItemDto(Item item) {
        this.itemId = item.getId();
        this.image = item.getFile().getFileUrl();
        this.itemName = item.getItemName();
    }
}
