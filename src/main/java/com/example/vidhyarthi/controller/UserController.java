package com.example.vidhyarthi.controller;


import com.example.vidhyarthi.Request.UserRequest;
import com.example.vidhyarthi.entity.User;
import com.example.vidhyarthi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody UserRequest user) {
        return userService.addUser(user);
    }

//    @PostConstruct
//    public void addAdmin() {
//        userService.initUser();
//    }
}
