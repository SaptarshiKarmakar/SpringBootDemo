package com.demo15.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "Employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;
    @Column(name= "number", length = 10, nullable = false, unique = true)
    private String number;
    @Column(name = "dept", length = 50, nullable = false)
    private String dept;
}
