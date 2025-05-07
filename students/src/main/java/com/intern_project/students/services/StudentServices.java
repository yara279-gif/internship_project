package com.intern_project.students.services;

import com.intern_project.students.models.student;
import com.intern_project.students.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServices {
    @Autowired
    private StudentRepo studentRepo;


    public student getStudentById(int id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    //-----------------(Register)-----------------------------------------------------
    public student registerAsStudent(student st) {
        if (studentRepo.existsByEmail(st.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return studentRepo.save(st) ;
    }

    //------------------------------------------------------------------------------
    //-----------------(UpdateProfile)-----------------------------------------------------
    public String updateProfile(student student, int id) {
        Optional<student> optionalStudent = studentRepo.findById(id);

        if (optionalStudent.isPresent()) {
            student existing = optionalStudent.get();

            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setPassword(student.getPassword());
            existing.setAddress(student.getAddress());
            existing.setAge(student.getAge());
            existing.setType("student");
            existing.setUniversity(student.getUniversity());
            existing.setCollege(student.getCollege());
            existing.setMajor(student.getMajor());
            existing.setLevel(student.getLevel());
            existing.setGpa(student.getGpa());

            studentRepo.save(existing);
            return "Profile updated successfully!";
        } else {
            return "Student not found!";
        }
    }
    //------------------------------------------------------------------------------
    //-----------------(Login)-----------------------------------------------------
    public String login(String email, String password) {
        Optional<student> studentOpt = studentRepo.findByEmail(email);

        if (studentOpt.isEmpty()) {
            return "User Not Found";
        }

        student st = studentOpt.get();
        if (st.getPassword().equals(password)) {
            return "Login Successful";
        } else {
            return "Password Incorrect";
        }
    }




}