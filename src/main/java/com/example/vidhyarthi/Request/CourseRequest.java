package com.example.vidhyarthi.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseRequest {
    @Pattern(regexp = "[a-zA-Z.]*", message = "Course name should start with alphabet not with number or special charcters")
    @Size(max = 20, message = "Course name cannot be more than 20 character long")
    @NotBlank(message = "name must be entered")
    private String name;
    @NotBlank(message = "enrolled year must be entered")
    @Pattern(regexp = "[0-9]*", message = "enrolled year  should be in number")
    @Min(value = 2075, message = "enrolled year must be greater than 2075")
    @Max(value = 2078, message = "enrolled year must be less than 2078")
    private String enrolledYear;
    @NotBlank(message = "duration must be entered")
    @Pattern(regexp = "[0-9]*", message = "duration  should be in number")
    private String duration;
    @NotBlank(message = "department name must be entered")
    @Pattern(regexp = "[a-zA-Z ]*", message = "Department name should start with alphabet not with number or special charcters")
    private String departmentName;
}
