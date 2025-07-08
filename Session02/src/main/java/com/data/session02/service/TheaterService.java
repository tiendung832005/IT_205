package com.data.session02.service;

import com.data.session02.entity.Theater;
import com.data.session02.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheaterService implements IService<Theater, Long> {

    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public Theater save(Theater theater) {
        return theaterRepository.save(theater);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater update(Theater theater) {
        return theaterRepository.save(theater);
    }

    @Override
    public void delete(Long id) {
        theaterRepository.deleteById(id);
    }
}