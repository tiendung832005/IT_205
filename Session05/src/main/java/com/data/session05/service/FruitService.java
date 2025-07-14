package com.data.session05.service;


import com.data.session05.entity.Fruit;
import com.data.session05.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {
    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }

    public Fruit getFruitById(Long id) {
        return fruitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fruit not found"));
    }

    public Fruit createFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public Fruit updateFruit(Long id, Fruit fruit) {
        Fruit existingFruit = getFruitById(id);
        existingFruit.setName(fruit.getName());
        existingFruit.setPrice(fruit.getPrice());
        existingFruit.setStock(fruit.getStock());
        existingFruit.setStatus(fruit.getStatus());
        existingFruit.setCreatedAt(fruit.getCreatedAt());
        return fruitRepository.save(existingFruit);
    }

    public void deleteFruit(Long id) {
        fruitRepository.deleteById(id);
    }
}
