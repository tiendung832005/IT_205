package com.data.session13.controller;


import com.data.session13.model.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PutMapping("/profile/edit")
    public String editProfile(@RequestBody User user) {
        // Edit profile logic
        return "Profile updated successfully!";
    }
}
