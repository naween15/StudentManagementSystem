package com.example.vidhyarthi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "COURSE_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    @NotNull(message = "Course must have its name")
    private String name;
    @Column(name = "ENROLLED_YEAR")
    @NotNull(message = "Enrolled year must be mentioned")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String enrolledYear;
    @Column(name = "DURATION")
    @NotNull(message = "duration cannot be empty")
    private String duration;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "id")
    private Department department;

}
