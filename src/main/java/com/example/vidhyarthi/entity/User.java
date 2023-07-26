package com.example.vidhyarthi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SYSTEM_USER_LOGIN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "STATUS")
//  should be either ACTIVE or INACTIVE
    private String status = "ACTIVE";
    @Column(name = "USERNAME", unique = true)
    private String userName;
    @Column(name = "ATTEMPT_COUNT")
    private int attemptCount = 0;
}
