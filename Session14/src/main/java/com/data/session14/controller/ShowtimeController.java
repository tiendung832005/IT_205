package com.data.session14.controller;

import com.data.session14.model.entity.Showtime;
import com.data.session14.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShowtimeController {
    private final ShowtimeService showtimeService;

    @GetMapping("/showtimes")
    public ResponseEntity<List<Showtime>> getShowtimes() {
        return ResponseEntity.ok(showtimeService.getAllShowtimes());
    }

    @PostMapping("/admin/showtimes")
    public ResponseEntity<Showtime> addShowtime(@RequestBody Showtime showtime) {
        return ResponseEntity.ok(showtimeService.addShowtime(showtime));
    }
}