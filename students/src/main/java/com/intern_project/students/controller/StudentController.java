package com.intern_project.students.controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.intern_project.students.dto.StudentResponse;
import com.intern_project.students.services.AuthServices;
import com.intern_project.students.services.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {


    private final StudentServices studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getUser(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllUsers() {
        return ResponseEntity.ok(studentService.getAllUsers());
    }
}
