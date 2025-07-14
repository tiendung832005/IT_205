package com.data.session02.service;

import com.data.session02.entity.Movie;
import com.data.session02.entity.ScreenRoom;
import com.data.session02.entity.Showtime;
import com.data.session02.repository.ShowtimeRepository;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService implements IService<Showtime, Long> {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public Showtime save(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public Showtime update(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public void delete(Long id) {
        showtimeRepository.deleteById(id);
    }

    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    public List<Showtime> filterShowtimes(Long movieId, String date, Long screenRoomId) {
        // Implement filtering logic in the repository or service layer.
        return showtimeRepository.filterByCriteria(movieId, date, screenRoomId);
    }

    public List<Movie> getAllMovies() {
        // Fetch all movies for the filter dropdown.
        ParseTreePattern movieRepository = null;
        return movieRepository.findAll();
    }

    public List<ScreenRoom> getAllScreenRooms() {
        // Fetch all screen rooms for the filter dropdown.
        return screenRoomRepository.findAll();
    }
}