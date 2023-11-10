package com.example.demo.service;

import com.example.demo.pojo.UsernotUse;

import java.util.List;

public interface UserService {
    List<UsernotUse> getAllUser();

    UsernotUse getUserById(Long id);

    void deleteUserById(Long id);

    void createUser(UsernotUse user);

    void updateUserById(UsernotUse user, Long id);
}
