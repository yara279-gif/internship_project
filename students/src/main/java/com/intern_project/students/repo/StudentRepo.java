package com.intern_project.students.repo;
import com.intern_project.students.models.student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<student, Integer> {
    Optional<student> findByEmail(String email);
    boolean existsByEmail(String email);
}
