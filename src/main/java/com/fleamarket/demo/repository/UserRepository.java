package com.fleamarket.demo.repository;

import com.fleamarket.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String var1);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
