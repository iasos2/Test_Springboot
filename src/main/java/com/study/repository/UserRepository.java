package com.study.repository;

import com.study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);

}
