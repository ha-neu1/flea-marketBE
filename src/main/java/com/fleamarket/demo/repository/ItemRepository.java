package com.fleamarket.demo.repository;

import com.fleamarket.demo.model.Item;
import com.fleamarket.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByUser_Username(String username);
}
