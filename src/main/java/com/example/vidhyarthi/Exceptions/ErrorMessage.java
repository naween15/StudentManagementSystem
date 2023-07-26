package com.example.vidhyarthi.Exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    HttpStatus status;
    String message;

}
