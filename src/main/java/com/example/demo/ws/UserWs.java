package com.example.demo.ws;

import com.example.demo.pojo.UsernotUse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.USER)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserWs {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UsernotUse> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public UsernotUse getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody UsernotUse user) {
        userService.createUser(user);
    }

    @PutMapping("{id}")
    public void updateUserById(@PathVariable Long id, @RequestBody UsernotUse user) {
        userService.updateUserById(user,id);

    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);

    }

    }

