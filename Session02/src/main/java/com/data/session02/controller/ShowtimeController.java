package com.data.session02.controller;

import com.data.session02.entity.Showtime;
import com.data.session02.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping
    public String getShowtimes(
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Long screenRoomId,
            Model model) {
        List<Showtime> showtimes = showtimeService.filterShowtimes(movieId, date, screenRoomId);
        model.addAttribute("showtimes", showtimes);
        model.addAttribute("movies", showtimeService.getAllMovies());
        model.addAttribute("screenRooms", showtimeService.getAllScreenRooms());
        return "showtime-list"; // Tên trang HTML hiển thị danh sách lịch chiếu.
    }

    @GetMapping("/add")
    public String showAddShowtimeForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        return "showtime-add"; // Tên trang HTML chứa form thêm lịch chiếu.
    }

    @PostMapping("/add")
    public String addShowtime(@ModelAttribute Showtime showtime) {
        showtimeService.save(showtime);
        return "redirect:/showtimes"; // Chuyển về trang danh sách lịch chiếu.
    }

    @GetMapping("/edit/{id}")
    public String showEditShowtimeForm(@PathVariable Long id, Model model) {
        Showtime showtime = showtimeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid showtime ID: " + id));
        model.addAttribute("showtime", showtime);
        return "showtime-edit"; // Tên trang HTML chứa form sửa lịch chiếu.
    }

    @PostMapping("/edit/{id}")
    public String editShowtime(@PathVariable Long id, @ModelAttribute Showtime showtime) {
        showtime.setId(id);
        showtimeService.update(showtime);
        return "redirect:/showtimes"; // Chuyển về trang danh sách lịch chiếu.
    }

    @PostMapping("/delete/{id}")
    public String deleteShowtime(@PathVariable Long id) {
        showtimeService.delete(id);
        return "redirect:/showtimes"; // Chuyển về trang danh sách lịch chiếu.
    }
}