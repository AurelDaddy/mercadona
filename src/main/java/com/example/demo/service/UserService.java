package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getUserById(Long id);

    void deleteUserById(Long id);

    void createUser(User user);

    void updateUserById(User user, Long id);
}
