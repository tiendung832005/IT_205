package com.data.session13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleController {

    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin access granted!";
    }

    @GetMapping("/editor")
    public String editorAccess() {
        return "Editor access granted!";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User access granted!";
    }
}
