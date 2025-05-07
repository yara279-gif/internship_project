package com.intern_project.students.controller;

import com.intern_project.students.models.student;
import com.intern_project.students.services.StudentServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/student")

public class StudentController {
    private final StudentServices studentService;

    public StudentController(StudentServices studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/register")
    public ResponseEntity<student> registerStudent(@RequestBody student st) {
        student savedStudent = studentService.registerAsStudent(st);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<String> update_profile(@PathVariable int id, @RequestBody student student) {
        String result = studentService.updateProfile(student, id);
        if (result.equals("Profile updated successfully!")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        String result = studentService.login(email, password);

        return switch (result) {
            case "Login Successful" -> ResponseEntity.ok("Login Successful");
            case "Password Incorrect" -> ResponseEntity.status(401).body("Password Incorrect");
            case "User Not Found" -> ResponseEntity.status(404).body("User Not Found");
            default -> ResponseEntity.status(500).body("Unknown Error");
        };
    }

    //    @PostMapping("/submitapplication")
//    public ResponseEntity<String> submit_app(@RequestBody Application app) {
//        app.setStatus(Pending);
//        // Directly call ApplicationService using FeignClient
//        appClient.submitApplication(app);
//        return ResponseEntity.ok("Application submitted successfully!");
//    }
    @GetMapping("/get/{id}")
    public ResponseEntity<student> getStudentById(@PathVariable int id) {
        student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

}