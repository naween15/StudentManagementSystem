package com.example.vidhyarthi.Request;

import com.example.vidhyarthi.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
    @NotBlank(message = "Department name must be entered")
    @Pattern(regexp = "[a-zA-Z]*", message = "Course name should start with alphabet not with number or special charcters")
    @Size(max = 40, message = "Department name name cannot be more than 40 character long")
    private String name;
    @NotBlank(message = "You must mention department's HOD")
    @Pattern(regexp = "[a-zA-Z]*", message = "HOD name should  start with alphabet not with number or special charcters")
    private String hod;
    @Size(max = 10)
    @NotBlank(message = "You must mention department's contact number")
    private String contactNo;
    @NotBlank(message = "you must mention the list of courses it provides")
    private List<Course> course;
}
