package com.data.session16.service;

import com.data.session16.model.entity.PlayArea;
import com.data.session16.repository.PlayAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayAreaService {
    private final PlayAreaRepository playAreaRepository;

    public List<PlayArea> getAllPlayAreas() {
        return playAreaRepository.findAll();
    }

    public PlayArea createPlayArea(PlayArea playArea) {
        return playAreaRepository.save(playArea);
    }

    public PlayArea updatePlayArea(Long id, PlayArea updatedPlayArea) {
        PlayArea playArea = playAreaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlayArea not found"));
        playArea.setName(updatedPlayArea.getName());
        playArea.setDescription(updatedPlayArea.getDescription());
        playArea.setMaxCapacity(updatedPlayArea.getMaxCapacity());
        playArea.setStatus(updatedPlayArea.getStatus());
        return playAreaRepository.save(playArea);
    }

    public void deletePlayArea(Long id) {
        if (!playAreaRepository.existsById(id)) {
            throw new RuntimeException("PlayArea not found");
        }
        playAreaRepository.deleteById(id);
    }
}