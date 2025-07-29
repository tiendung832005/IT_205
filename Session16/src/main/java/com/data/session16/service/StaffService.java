package com.data.session16.service;

import com.data.session16.model.entity.Staff;
import com.data.session16.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    public Staff createStaff(Staff staff) {
        if (staffRepository.findByEmail(staff.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return staffRepository.save(staff);
    }

    public Staff updateStaffRole(Long id, Staff.Role role) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        staff.setRole(role);
        return staffRepository.save(staff);
    }

    public Staff getStaffByEmail(String email) {
        return staffRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }

    public Staff updatePersonalInfo(String email, Staff updatedInfo) {
        Staff staff = getStaffByEmail(email);
        staff.setName(updatedInfo.getName());
        staff.setPhone(updatedInfo.getPhone());
        return staffRepository.save(staff);
    }
}