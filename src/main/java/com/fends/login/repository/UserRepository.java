package com.fends.login.repository;

import com.fends.login.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByName(String name);
    UserModel findByUsername(String username);
}