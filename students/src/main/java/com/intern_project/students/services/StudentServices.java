package com.intern_project.students.services;

import com.intern_project.students.dto.StudentResponse;
import com.intern_project.students.models.student;
import com.intern_project.students.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServices {

    private final StudentRepo studentRepo;

    public StudentResponse getUserById(int id) {
        student st  = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToDto(st);
    }

    public List<StudentResponse> getAllUsers() {
        return studentRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private StudentResponse mapToDto(student st) {
        return new StudentResponse(
                st.getUser_id(),
                st.getName(),
                st.getEmail(),
                st.getAddress(),
                st.getAge(),
                st.getType(),
                st.getUniversity(),
                st.getCollege(),
                st.getMajor(),
                st.getLevel(),
                st.getGpa()
        );
    }


}
