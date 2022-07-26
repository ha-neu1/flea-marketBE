package com.fleamarket.demo.repository;

import com.fleamarket.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByUser_Username(String username);
    Optional<Item> findByItemName(String itemName);
    List<Item> findByItemNameAndFileFileUrl(String itemName, Resource image);

}
