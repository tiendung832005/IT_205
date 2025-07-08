package com.data.session02.service;

import com.data.session02.entity.Showtime;
import com.data.session02.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}