package com.data.session08.controller;

import com.data.session08.dto.DishDTO;
import com.data.session08.entity.Dish;
import com.data.session08.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<Dish> addDish(@ModelAttribute DishDTO dishDTO) {
        try {
            Dish dish = dishService.addDish(dishDTO);
            return ResponseEntity.ok(dish);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @ModelAttribute DishDTO dishDTO) {
        try {
            Dish dish = dishService.updateDish(id, dishDTO);
            return ResponseEntity.ok(dish);
        } catch (IOException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) {
        try {
            dishService.deleteDish(id);
            return ResponseEntity.ok("Dish deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return ResponseEntity.ok(dishes);
    }
}