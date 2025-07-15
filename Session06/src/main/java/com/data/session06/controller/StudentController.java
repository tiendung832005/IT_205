package com.data.session06.controller;

import com.data.session06.entity.Student;
import com.data.session06.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") String studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") String studentId) {
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") String studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}