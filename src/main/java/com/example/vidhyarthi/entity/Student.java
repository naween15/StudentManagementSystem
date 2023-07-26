package com.example.vidhyarthi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @Column(name = "FIRST_NAME", nullable = false)
//    @NotBlank(message = "first name cannot be empty")
//    @Size(min = 8, message = "name cannot be less than 8 character long")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
//    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @Column(name = "GENDER")

    private String gender;

    @Column(name = "DATE_OF_BIRTH")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dateOfBirth;

    @Column(name = "STATUS")
    private String status = "INACTIVE";

    @Column(name = "CITIZENSHIP_NO")
//    @NotBlank(message = "Citizenship number cannot be blank")
    private String citizenshipNumber;

    @Column(name = "ADDRESS")
//    @NotBlank(message = "Address number cannot be blank")
    private String address;

    @Column(name = "MARITAL_STATUS")
//    @NotBlank(message = "Marital Status number cannot be blank")
    private String maritalStatus;

    @Column(name = "EMAIL_ADDRESS")
//    @NotBlank(message = "Email Address number cannot be blank")
    private String emailAddress;

    @Column(name = "MOBILE_NUMBER", unique = true)
//    @NotBlank(message = "Mobile number number cannot be blank")
    private String mobileNumber;

    @Column(name = "ADDED_BY")
//    @NotBlank(message = "Added_by  cannot be blank")
    private String addedBy;

    @CreationTimestamp
    @Column(name = "ADDED_DATE")
    private Date addedDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    private Course course;

}
