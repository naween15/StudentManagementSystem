package com.example.vidhyarthi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentPayload {
    private String firstName;
    private String middleName;
    private String lastName;
    private String citizenshipNumber;
    private String Address;
    private String emailAddress;
    private String mobileNumber;
    private String Gender;
    private String maritalStatus;
    private String departmentName;
    private String courseName;
    private String dateOfBirth;
    private String enrolledYear;
    private String moodifiedDate;
    private String status;
    private List<EducationQualificationPayload> educationalQualification;

}
