package com.example.vidhyarthi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Studentdetails {
    @NotBlank(message = "name must be entered")
    private String name;
    @NotBlank(message = "gender must be specified")
    private String gender;
    @NotBlank(message = "marital status must be specified")
    private String maritalStatus;
    @NotBlank(message = "added by must be specified")
    private String addedBy;
    @NotBlank(message = "modified by must be specified")
    private String modifiedBy;
    @NotBlank(message = "contact must be specified")
    private String contact;
    @NotBlank(message = "department name must be specified")
    private String department;
    @NotBlank(message = "course must be specified")
    private String course;
    @NotBlank(message = "enrolled year must be specified")
    private String enrolledYear;
    @NotBlank(message = "status must be specified")
    private String status;
    private String modifiedDate;
}
