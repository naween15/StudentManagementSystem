package com.example.vidhyarthi.Request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthRequest {
    @NotBlank(message = "username cannot be blank")
    @Pattern(regexp = "[a-zA-Z]+[1-9]*", message = "Username should start with alphabet not with number or special charcters")
    @Size(max = 20, message = "username cannot be more than 20 character long")
    private String userName;
    @NotBlank(message = "password cannot be blank")
    @Size(min = 8, message = "password cannot be less than 8 character long")
    private String password;
}
