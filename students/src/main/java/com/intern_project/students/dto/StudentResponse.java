package com.intern_project.students.dto;

public class StudentResponse {
    private int userId;
    private String name;
    private String email;
    private String address;
    private int age;
    private String type;
    private String university;
    private String college;
    private String major;
    private int level;
    private float gpa;

    // Full Constructor
    public StudentResponse(int userId, String name, String email, String address, int age, String type,
                           String university, String college, String major, int level, float gpa) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.age = age;
        this.type = type;
        this.university = university;
        this.college = college;
        this.major = major;
        this.level = level;
        this.gpa = gpa;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public float getGpa() { return gpa; }
    public void setGpa(float gpa) { this.gpa = gpa; }
}
