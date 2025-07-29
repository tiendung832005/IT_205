package com.data.session16.controller;

import com.data.session16.model.entity.Combo;
import com.data.session16.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
@RequiredArgsConstructor
public class ComboController {
    private final ComboService comboService;

    @GetMapping
    public ResponseEntity<List<Combo>> getAllCombos() {
        return ResponseEntity.ok(comboService.getAllCombos());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Combo> createCombo(@RequestBody Combo combo) {
        return ResponseEntity.ok(comboService.createCombo(combo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Combo> updateCombo(@PathVariable Long id, @RequestBody Combo combo) {
        return ResponseEntity.ok(comboService.updateCombo(id, combo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Void> deleteCombo(@PathVariable Long id) {
        comboService.deleteCombo(id);
        return ResponseEntity.noContent().build();
    }
}