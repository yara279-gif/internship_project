package com.intern_project.students.dto;

import lombok.Data;

import java.util.Set;
@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String address; // Required field
    private int age;       // Required field
    private String type;   // Required field
    private String university; // Required field
    private String college;    // Required field
    private String major;      // Required field
    private int level;        // Required field
    private float gpa;       // Required field
    private Set<Long> applicationIds; // Optional field
}
