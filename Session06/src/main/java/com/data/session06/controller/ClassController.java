package com.data.session06.controller;

import com.data.session06.entity.Class;
import com.data.session06.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public Class createClass(@RequestBody Class classEntity) {
        return classService.createClass(classEntity);
    }

    @PutMapping("/{id}")
    public Class updateClass(@PathVariable("id") String classId, @RequestBody Class classEntity) {
        return classService.updateClass(classId, classEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable("id") String classId) {
        classService.deleteClass(classId);
    }

    @GetMapping("/{id}")
    public Class getClassById(@PathVariable("id") String classId) {
        return classService.getClassById(classId);
    }

    @GetMapping
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }
}