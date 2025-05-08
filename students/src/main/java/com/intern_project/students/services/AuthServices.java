package com.intern_project.students.services;
import com.intern_project.students.dto.LoginRequest;
import com.intern_project.students.dto.ForgetPasswordRequest;
import com.intern_project.students.dto.RegisterRequest;
import com.intern_project.students.dto.ResetPasswordRequest;
import com.intern_project.students.models.student;
import com.intern_project.students.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final StudentRepo studentRepo;

    private final PasswordEncoder passwordEncoder;


    public String register(RegisterRequest request) {
        if (studentRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        String email = request.getEmail().trim().toLowerCase();
        String encodedPassword = passwordEncoder.encode(request.getPassword().trim());

        student student = new student();
        student.setName(request.getName());
        student.setEmail(email);
        student.setPassword(encodedPassword);
        student.setAddress(request.getAddress()); // Required
        student.setAge(request.getAge()); // Required
        student.setType(request.getType()); // Required
        student.setUniversity(request.getUniversity()); // Required
        student.setCollege(request.getCollege()); // Required
        student.setMajor(request.getMajor()); // Required
        student.setLevel(request.getLevel()); // Required
        student.setGpa(request.getGpa()); // Required

        if (request.getApplicationIds() != null) {
            student.setApplicationIds(request.getApplicationIds());
        }
        studentRepo.save(student);


        return "User registered";
    }


    public String login(LoginRequest request) {
        // Normalize input
        String email = request.getEmail().trim().toLowerCase();
        String rawPassword = request.getPassword().trim();

        System.out.println("[LOGIN ATTEMPT]");
        System.out.println("Email: " + email);

        // Find user by email
        student student = studentRepo.findByEmail(email)
                .orElseThrow(() -> {
                    System.err.println("[ERROR] User not found: " + email);
                    return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
                });

        System.out.println("Stored Password Hash: " + student.getPassword());
        System.out.println("Match result: " + passwordEncoder.matches(rawPassword, student.getPassword()));

        // Compare raw password with stored hash
        if (!passwordEncoder.matches(rawPassword, student.getPassword())) {
            System.err.println("[ERROR] Password mismatch for: " + email);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        System.out.println("[LOGIN SUCCESS] for user: " + email);

        return "Login successful";
    }


    public String forgetPassword(ForgetPasswordRequest request) {
        student student = studentRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String resetToken = UUID.randomUUID().toString();
        student.setResetToken(resetToken); // حفظ الرمز في الحقل
        studentRepo.save(student); // تأكد من حفظ التغييرات

        return "Reset token: " + resetToken;
    }
    public String resetPassword(ResetPasswordRequest request) {
        System.out.println("Searching for token: " + request.getToken()); // Debug

        student student = studentRepo.findByResetToken(request.getToken())
                .orElseThrow(() -> {
                    System.err.println("Token not found: " + request.getToken()); // Debug
                    return new RuntimeException("Invalid token");
                });

        student.setPassword(passwordEncoder.encode(request.getNewPassword()));
        student.setResetToken(null); // مسح الرمز بعد الاستخدام
        studentRepo.save(student);

        return "Password updated successfully";
    }
    public String logout() {
        return "Logout successful";
    }
}
