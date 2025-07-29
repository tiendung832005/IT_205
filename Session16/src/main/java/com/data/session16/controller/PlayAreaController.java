package com.data.session16.controller;

import com.data.session16.model.entity.PlayArea;
import com.data.session16.service.PlayAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/play-areas")
@RequiredArgsConstructor
public class PlayAreaController {
    private final PlayAreaService playAreaService;

    @GetMapping
    public ResponseEntity<List<PlayArea>> getAllPlayAreas() {
        return ResponseEntity.ok(playAreaService.getAllPlayAreas());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<PlayArea> createPlayArea(@RequestBody PlayArea playArea) {
        return ResponseEntity.ok(playAreaService.createPlayArea(playArea));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<PlayArea> updatePlayArea(@PathVariable Long id, @RequestBody PlayArea playArea) {
        return ResponseEntity.ok(playAreaService.updatePlayArea(id, playArea));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Void> deletePlayArea(@PathVariable Long id) {
        playAreaService.deletePlayArea(id);
        return ResponseEntity.noContent().build();
    }
}