package com.fleamarket.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fleamarket.demo.model.Eembbed.File;
import com.fleamarket.demo.model.dto.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int itemPrice;

    @Column(nullable = false)
    private String itemDetail;

    @Embedded
    private File file;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<Comment> comments = new ArrayList<>();

    public Item(ItemDto itemDto) {

        this.itemName = itemDto.getItemName();
        this.itemPrice = itemDto.getItemPrice();
        this.itemDetail = itemDto.getItemDetail();
    }

    public void setFile(File file) {
        this.file = file;
    }

    // 연관 관계 메소드
    public void setRelationUser(User user) {
        this.user = user;
        user.getItemList().add(this);
    }
}
