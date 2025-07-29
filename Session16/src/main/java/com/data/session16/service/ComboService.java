package com.data.session16.service;

import com.data.session16.model.entity.Combo;
import com.data.session16.repository.ComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboService {
    private final ComboRepository comboRepository;

    public List<Combo> getAllCombos() {
        return comboRepository.findAll();
    }

    public Combo createCombo(Combo combo) {
        return comboRepository.save(combo);
    }

    public Combo updateCombo(Long id, Combo updatedCombo) {
        Combo combo = comboRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Combo not found"));
        combo.setName(updatedCombo.getName());
        combo.setDescription(updatedCombo.getDescription());
        combo.setPrice(updatedCombo.getPrice());
        combo.setItems(updatedCombo.getItems());
        combo.setStatus(updatedCombo.getStatus());
        return comboRepository.save(combo);
    }

    public void deleteCombo(Long id) {
        if (!comboRepository.existsById(id)) {
            throw new RuntimeException("Combo not found");
        }
        comboRepository.deleteById(id);
    }
}