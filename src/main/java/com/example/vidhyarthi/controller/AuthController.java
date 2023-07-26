package com.example.vidhyarthi.controller;

import com.example.vidhyarthi.Request.AuthRequest;
import com.example.vidhyarthi.Response.ResponseObject;
import com.example.vidhyarthi.config.JwtUtil;
import com.example.vidhyarthi.config.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
//@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private JwtUtil jwtUtil;

    //    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) throws Exception {
//        AuthResponse authResponse = userAuthService.createJwtToken(request);
//        return new ResponseEntity<>(authResponse, HttpStatus.OK);
//    }
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthRequest request) {
        try {
            ResponseObject responseObject = userAuthService.createJwtToken(request);
            System.out.println(responseObject.getMessage());
            return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("Something went wrong");
        }
    }
}
