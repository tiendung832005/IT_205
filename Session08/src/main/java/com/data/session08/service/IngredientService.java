package com.data.session08.service;

import com.data.session08.entity.Ingredient;
import com.data.session08.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Ingredient addIngredient(String name, Integer stock, LocalDate expiry, MultipartFile image) throws IOException {
        String imageUrl = cloudinaryService.uploadImage(image);
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setStock(stock);
        ingredient.setExpiry(expiry);
        ingredient.setImage(imageUrl);
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, String name, Integer stock, LocalDate expiry, MultipartFile image) throws IOException {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (optionalIngredient.isEmpty()) {
            throw new NoSuchElementException("Ingredient not found with id: " + id);
        }
        Ingredient ingredient = optionalIngredient.get();
        ingredient.setName(name);
        ingredient.setStock(stock);
        ingredient.setExpiry(expiry);
        if (image != null && !image.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(image);
            ingredient.setImage(imageUrl);
        }
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new NoSuchElementException("Ingredient not found with id: " + id);
        }
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}