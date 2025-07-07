package com.data.session01.controller;

import com.data.session01.Entity.Student;
import com.data.session01.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public String listStudent(Model model) {
        model.addAttribute("students", StudentRepository.getStudents());
        return "student-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("student") Student student) {
        StudentRepository.addStudent(student);
        return "redirect:/student";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        StudentRepository.deleteStudent(id);
        return "redirect:/student";
    }
}
