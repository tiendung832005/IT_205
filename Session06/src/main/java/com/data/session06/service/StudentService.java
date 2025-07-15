package com.data.session06.service;

import com.data.session06.entity.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(String studentId, Student student);
    void deleteStudent(String studentId);
    Student getStudentById(String studentId);
    List<Student> getAllStudents();
}