package com.example.vidhyarthi.service;


import com.example.vidhyarthi.Request.UserRequest;
import com.example.vidhyarthi.entity.User;

public interface UserService {
    public User addUser(UserRequest user);

    public void initUser();
}
