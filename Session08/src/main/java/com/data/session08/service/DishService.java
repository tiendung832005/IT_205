package com.data.session08.service;

import com.data.session08.dto.DishDTO;
import com.data.session08.entity.Dish;
import com.data.session08.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Dish addDish(DishDTO dishDTO) throws IOException {
        String imageUrl = cloudinaryService.uploadImage(dishDTO.getImage());
        Dish dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setDescription(dishDTO.getDescription());
        dish.setPrice(dishDTO.getPrice());
        dish.setStatus(dishDTO.getStatus());
        dish.setImage(imageUrl);
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, DishDTO dishDTO) throws IOException {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isEmpty()) {
            throw new NoSuchElementException("Dish not found with id: " + id);
        }
        Dish dish = optionalDish.get();
        dish.setName(dishDTO.getName());
        dish.setDescription(dishDTO.getDescription());
        dish.setPrice(dishDTO.getPrice());
        dish.setStatus(dishDTO.getStatus());
        if (dishDTO.getImage() != null && !dishDTO.getImage().isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(dishDTO.getImage());
            dish.setImage(imageUrl);
        }
        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new NoSuchElementException("Dish not found with id: " + id);
        }
        dishRepository.deleteById(id);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
}