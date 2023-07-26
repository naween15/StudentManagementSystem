package com.example.vidhyarthi.serviceImpl;

import com.example.vidhyarthi.Request.UserRequest;
import com.example.vidhyarthi.entity.User;
import com.example.vidhyarthi.repository.UserRepository;
import com.example.vidhyarthi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(UserRequest userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public void initUser() {
        User user = new User();
        user.setUserName("demo");
        user.setPassword(passwordEncoder.encode("demo"));
        userRepository.save(user);
    }
}
