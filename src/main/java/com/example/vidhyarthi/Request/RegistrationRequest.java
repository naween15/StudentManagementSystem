package com.example.vidhyarthi.Request;

import com.example.vidhyarthi.entity.EducationDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "first name cannot be empty")
    @Pattern(regexp = "[a-zA-Z.]*", message = "first name cant start with number or special characters")
    private String firstName;
    @Pattern(regexp = "[a-zA-Z]*", message = "middle name cant start with number or special characters")
    private String middleName;
    @NotBlank(message = "lastname cannot be empty")
    @Pattern(regexp = "[a-zA-Z]*", message = "last name cant start with number or special characters")
    private String lastName;
    @NotBlank(message = "gender cannot be empty")
    @Pattern(regexp = "[a-zA-Z]*", message = "gender cant start with number or special characters")
    private String gender;
    @JsonFormat(pattern = "YYYY-MM-DD")
    @NotBlank(message = "date of birth cannot be empty")
    private String dateOfBirth;
    @NotBlank(message = "citizenship number cannot be empty")
    private String citizenshipNumber;
    @NotBlank(message = "address cannot be empty")
    @Pattern(regexp = "[a-zA-Z]*", message = "address cant start with number or special characters")
    private String address;
    @NotBlank(message = "gender cannot be empty")
    @Pattern(regexp = "[a-zA-Z]*", message = "marital status cant start with number or special characters")
    private String maritalStatus;
    @NotBlank(message = "gender cannot be empty")
    @Email(message = "email should be of standard format")
    private String emailAddress;
    @NotBlank(message = "mobileNumber cannot be empty")
    @Pattern(regexp = "[0-9]*", message = "marital status cant start with number or special characters")
    @Size(min = 10, max = 10, message = "number cannot be greater than 10 or less than 10")
    private String mobileNumber;
    @NotBlank(message = "departmentName cannot be empty")
    @Pattern(regexp = "[a-zA-Z ]*", message = "department name cant start with number or special characters")
    private String departmentName;
    @NotBlank(message = "courseName cannot be empty")
    @Pattern(regexp = "[a-zA-Z.]*", message = "course name cant start with number or special characters")
    @Size(max = 30, message = "course name cannot be bigger than 30")
    private String courseName;
    @NotBlank(message = "enrolled year cannot be empty")
    @Min(value = 2075, message = "Enrolled year cannot be less than 2075")
    @Max(value = 2078, message = "Enrolled year cannot be more than 2078")
    private String enrolledYear;
    private List<EducationDetails> educational_details;
}
