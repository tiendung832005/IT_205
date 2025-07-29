package com.data.session16.controller;

import com.data.session16.model.entity.Staff;
import com.data.session16.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/staffs")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.createStaff(staff));
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Staff> updateStaffRole(@PathVariable Long id, @RequestParam Staff.Role role) {
        return ResponseEntity.ok(staffService.updateStaffRole(id, role));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Staff> getPersonalInfo(Principal principal) {
        return ResponseEntity.ok(staffService.getStaffByEmail(principal.getName()));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Staff> updatePersonalInfo(Principal principal, @RequestBody Staff updatedInfo) {
        return ResponseEntity.ok(staffService.updatePersonalInfo(principal.getName(), updatedInfo));
    }
}