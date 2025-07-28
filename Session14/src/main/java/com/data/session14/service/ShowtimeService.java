package com.data.session14.service;

import com.data.session14.model.entity.Showtime;
import com.data.session14.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public Showtime addShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }
}