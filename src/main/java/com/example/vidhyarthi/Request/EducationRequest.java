package com.example.vidhyarthi.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequest {
    @NotBlank(message = "level must be entered")
    private String level;
    @NotBlank(message = "course must be specified")
    private String course;
    @NotBlank(message = "institute name must be specified")
    private String instituteName;
    @NotBlank(message = "university name must be specified")
    private String universityName;
//    @NotBlank(message = "student details must be shared")
//    private Student student;


}
