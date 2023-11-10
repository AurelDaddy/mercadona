package com.example.demo.service.impl;

import com.example.demo.pojo.UsernotUse;
import com.example.demo.repository.UserNotUseRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserNotUseRepository userRepository;

    @Override
    public List<UsernotUse> getAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public UsernotUse getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void createUser(UsernotUse user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserById(UsernotUse user, Long id) {
        UsernotUse oldUser = getUserById(id);

        if (oldUser != null) {
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setRoles(user.getRoles());
            userRepository.save(oldUser);

        }
    }
}
