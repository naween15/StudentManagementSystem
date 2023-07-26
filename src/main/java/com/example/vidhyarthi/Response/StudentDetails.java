package com.example.vidhyarthi.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetails {
    @NotBlank(message = "name must be entered")
    private String name;
    @NotBlank(message = "gender must be entered")
    private String gender;
    @NotBlank(message = "marital status must be entered")
    private String maritalStatus;
    @NotBlank(message = "added by must be entered")
    private String addedBy;
    @NotBlank(message = "modified by must be entered")
    private String modifiedBy;
    @NotBlank(message = "contact must be entered")
    private String contact;
    @NotBlank(message = "department must be entered")
    private String department;
    @NotBlank(message = "course name must be entered")
    private String course;
    @NotBlank(message = "enrolled year must be entered")
    private String enrolledYear;
    private String modifiedDate;
}
