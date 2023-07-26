package com.example.vidhyarthi.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {
    @NotBlank(message = "course name must be entered")
    @Pattern(regexp = "[a-zA-Z]*", message = "Course name should start with alphabet not with number or special charcters")
    @Size(max = 20, message = "Course name cannot be more than 20 character long")
    private String name;
    @NotBlank(message = "enrolled year must be specified")
    @Min(2075)
    @Max(2078)
    private LocalDate enrolledYear;
}
