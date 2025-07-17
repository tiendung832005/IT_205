package com.data.session08.controller;

import com.data.session08.entity.Ingredient;
import com.data.session08.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(
            @RequestParam String name,
            @RequestParam Integer stock,
            @RequestParam LocalDate expiry,
            @RequestParam MultipartFile image) {
        try {
            Ingredient ingredient = ingredientService.addIngredient(name, stock, expiry, image);
            return ResponseEntity.ok(ingredient);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Integer stock,
            @RequestParam LocalDate expiry,
            @RequestParam(required = false) MultipartFile image) {
        try {
            Ingredient ingredient = ingredientService.updateIngredient(id, name, stock, expiry, image);
            return ResponseEntity.ok(ingredient);
        } catch (IOException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable Long id) {
        try {
            ingredientService.deleteIngredient(id);
            return ResponseEntity.ok("Ingredient deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }
}