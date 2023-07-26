package com.example.vidhyarthi.config;

import com.example.vidhyarthi.Request.AuthRequest;
import com.example.vidhyarthi.Response.AuthResponse;
import com.example.vidhyarthi.Response.ResponseObject;
import com.example.vidhyarthi.entity.User;
import com.example.vidhyarthi.repository.UserRepository;
import com.example.vidhyarthi.util.ResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseObject createJwtToken(AuthRequest jwtRequest) {
        try {
            String userName = jwtRequest.getUserName();
            String userPassword = jwtRequest.getPassword();
            User user = userRepository.findByUserName(userName);
            if (user == null) {
                return ResponseUtility.resourceNotFound(userName, "Username Not found", HttpStatus.OK);
            }
            if (user.getAttemptCount() >= 3) {
                user.setStatus("INACTIVE");
                userRepository.save(user);
                return ResponseUtility.resourceNotFound(userName, "Sorry!! you are disabled", HttpStatus.OK);
            }
            ResponseObject responseObject = authenticate(userName, userPassword);
            if (responseObject.getMessage().equalsIgnoreCase("password didnt matched")) {
                return responseObject;
            } else if (responseObject.getMessage().equalsIgnoreCase("Not Found")) {
                return responseObject;
            } else {
                UserDetails userDetails = loadUserByUsername(userName);
                User authenticatedUser = userRepository.findByUserName(userName);
                authenticatedUser.setAttemptCount(0);
                userRepository.save(authenticatedUser);
                String newGeneratedToken = jwtUtil.generateToken(userDetails);
                return ResponseUtility.resourceFound(new AuthResponse(newGeneratedToken), "Authenticated", HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseUtility.resourceNotFound(null, "something went wrong", HttpStatus.CONFLICT);
        }
    }

    private ResponseObject authenticate(String userName, String userPassword) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
            return ResponseUtility.resourceFound(userName, "Authenticated", HttpStatus.OK);
        } catch (Exception e) {
            User user = userRepository.findByUserName(userName);
            if (user.getAttemptCount() <= 3) {
                int count = user.getAttemptCount();
                user.setAttemptCount(count + 1);
                userRepository.save(user);
                return ResponseUtility.resourceNotFound((3 - user.getAttemptCount()), "password didnt matched", HttpStatus.OK);
            } else {
                return ResponseUtility.resourceNotFound(null, "Something went wring try again", HttpStatus.OK);
            }
        }
    }

    private Set getAuthority() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(), user.getPassword(),
                    getAuthority());
        } else {
            throw new UsernameNotFoundException("username is not valid");
        }
    }
}

//    public AuthResponse createJwtToken(AuthRequest jwtRequest) {
//        try {
//            String userName = jwtRequest.getUserName();
//            String userPassword = jwtRequest.getPassword();
//            User user = userRepository.findByUserName(userName);
//            if (user == null) {
//                throw new ResourceNotFoundException("No any user with username: " + userName + "not found");
//            }
//            if (user.getAttemptCount() >= 3) {
//                user.setStatus("INACTIVE");
//                userRepository.save(user);
//                throw new DisabledException("Sorry!! " + userName + " is disabled");
//            }
//            authenticate(userName, userPassword);
//            UserDetails userDetails = loadUserByUsername(userName);
//            User authenticatedUser = userRepository.findByUserName(userName);
//            authenticatedUser.setAttemptCount(0);
//            userRepository.save(authenticatedUser);
//            String newGeneratedToken = jwtUtil.generateToken(userDetails);
//            return new AuthResponse(user, newGeneratedToken);
//        } catch (Exception e) {
//            throw new ResourceNotFoundException(jwtRequest.getUserName() + " Not found");
//        }
//    }

//    private void authenticate(String userName, String userPassword) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
//        } catch (DisabledException e) {
//            throw new Exception("User is disabled");
//        } catch (BadCredentialsException e) {
//            User user = userRepository.findByUserName(userName);
//            if (user == null) {
//                throw new Exception("Bad credential from user");
//            } else {
//                if (user.getAttemptCount() <= 3) {
//                    int count = user.getAttemptCount();
//                    user.setAttemptCount(count + 1);
//                    userRepository.save(user);
//                    throw new Exception("Password didnt matched " + (3 - user.getAttemptCount()) + ".Attempt left");
//                } else {
//                    throw new Exception(("User is disabled"));
//                }
//            }
//        }
//    }

