package com.fleamarket.demo.repository;

import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
