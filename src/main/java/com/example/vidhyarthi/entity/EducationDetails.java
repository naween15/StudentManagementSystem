package com.example.vidhyarthi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EDUCATIONAL_DETAILS")
@Getter
@Setter
public class EducationDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "LEVEL")
    private String level;
    @Column(name = "COURSE")
    private String course;
    @Column(name = "INSTITUTE_NAME")
    private String instituteName;
    @Column(name = "UNIVERSITY_NAME")
    private String universityName;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;
}
