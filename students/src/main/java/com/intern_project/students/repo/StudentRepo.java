package com.intern_project.students.repo;
import com.intern_project.students.models.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepo extends JpaRepository<student, Integer> {

    boolean existsByEmail(String email);
    Optional<student> findByEmailAndPassword(String email, String password);
    Optional<student> findByEmail(String email);
    Optional<student> findByResetToken(String resetToken);
}
