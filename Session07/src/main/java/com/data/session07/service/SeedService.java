package com.data.session07.service;

import com.data.session07.entity.Seed;
import com.data.session07.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedService {
    @Autowired
    private SeedRepository seedRepository;

    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    public Seed getSeedById(Long id) {
        return seedRepository.findById(id).orElse(null);
    }

    public Seed addSeed(Seed seed) {
        return seedRepository.save(seed);
    }

    public Seed updateSeed(Long id, Seed seed) {
        seed.setId(id);
        return seedRepository.save(seed);
    }

    public void deleteSeed(Long id) {
        seedRepository.deleteById(id);
    }
}
