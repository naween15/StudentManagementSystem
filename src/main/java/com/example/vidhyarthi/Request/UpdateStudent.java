package com.example.vidhyarthi.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudent {
    @NotBlank(message = "first name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]*", message = "first name cant start with number or special characters")
    private String firstName;
    private String middleName;
    @NotBlank(message = "lastname cannot be empty")
    private String lastName;
    @NotBlank(message = "gender cannot be empty")
    private String gender;
    @JsonFormat(pattern = "YYYY-MM-DD")
    @NotBlank(message = "date of birth cannot be empty")
    private String dateOfBirth;
    @NotBlank(message = "citizenship number cannot be empty")
    private String citizenshipNumber;
    @NotBlank(message = "address cannot be empty")
    private String address;
    @NotBlank(message = "gender cannot be empty")
    private String maritalStatus;
    @NotBlank(message = "gender cannot be empty")
    @Email(message = "email should be of standard format")
    private String emailAddress;
    @NotBlank(message = "mobileNumber cannot be empty")
    private String mobileNumber;
}
