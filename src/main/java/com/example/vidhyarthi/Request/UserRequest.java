package com.example.vidhyarthi.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String password;
    private String status = "ACTIVE";
    private String userName;
    private int attemptCount = 0;
}
