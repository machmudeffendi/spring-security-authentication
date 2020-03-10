package com.fends.login.service;

import com.fends.login.model.UserModel;
import com.fends.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserModel findUserByName(String name){
        return userRepository.findByName(name);
    }

    public UserModel findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserModel saveUserAdmin(UserModel user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ADMIN");
        return userRepository.save(user);
    }
}
