package com.example.vidhyarthi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "HOD")
    private String hod;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Course> course;

}
