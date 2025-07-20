package com.data.session09.controller;

import com.data.session09.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final Map<Long, Movie> movieDatabase = new HashMap<>(); // Simulated database
    private final String logFilePath = "logs/app.log";
    private final String uploadDir = "uploads/";

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        try {
            movieDatabase.put(movie.getId(), movie); // Simulate saving the movie
            log.debug("Attempting to add movie: {}", movie.getTitle());
            log.info("Movie '{}' added successfully at {}", movie.getTitle(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.CREATED).body("Movie added successfully");
        } catch (Exception ex) {
            log.error("Error occurred while adding movie: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add movie");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        try {
            Movie existingMovie = movieDatabase.get(id);
            if (existingMovie == null) {
                throw new RuntimeException("Movie not found");
            }

            log.info("\u001B[33mOld Movie Info: {}\u001B[0m", existingMovie); // Yellow text
            movieDatabase.put(id, updatedMovie); // Simulate update
            log.info("\u001B[32mUpdated Movie Info: {}\u001B[0m", updatedMovie); // Green text

            return ResponseEntity.status(HttpStatus.OK).body("Movie updated successfully");
        } catch (Exception ex) {
            log.error("\u001B[31mError occurred while updating movie: {}\u001B[0m", ex.getMessage(), ex); // Red text
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update movie");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        try {
            Movie movieToDelete = movieDatabase.get(id);
            if (movieToDelete == null) {
                throw new RuntimeException("Movie not found");
            }

            movieDatabase.remove(id); // Simulate deletion
            log.info("\u001B[31mDeletion successful\u001B[0m \u001B[32mDeleted Movie Info: {}\u001B[0m", movieToDelete); // Red and green text

            return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully");
        } catch (Exception ex) {
            log.error("\u001B[31mError occurred while deleting movie: {}\u001B[0m", ex.getMessage(), ex); // Red text
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete movie");
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String searchMovie) {
        long startTime = System.currentTimeMillis();
        List<Movie> movies;

        if (searchMovie != null && !searchMovie.isEmpty()) {
            movies = movieDatabase.values().stream()
                    .filter(movie -> movie.getTitle().toLowerCase().contains(searchMovie.toLowerCase()))
                    .collect(Collectors.toList());
            log.info("\u001B[32mSearch parameter: '{}'\u001B[0m", searchMovie); // Green text
        } else {
            movies = List.copyOf(movieDatabase.values());
        }

        long executionTime = System.currentTimeMillis() - startTime;
        log.info("\u001B[32mNumber of movies returned: {}\u001B[0m", movies.size()); // Green text
        log.info("\u001B[32mExecution time: {} ms\u001B[0m", executionTime); // Green text

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search-logs")
    public ResponseEntity<Map<String, Integer>> getSearchLogs() {
        Map<String, Integer> searchKeywords = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Search parameter:")) {
                    String keyword = line.substring(line.indexOf("Search parameter:") + 17).trim();
                    searchKeywords.put(keyword, searchKeywords.getOrDefault(keyword, 0) + 1);
                }
            }
        } catch (IOException ex) {
            log.error("Error reading log file: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).body(Collections.emptyMap());
        }

        return ResponseEntity.ok(searchKeywords);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<Movie>> getSuggestions() {
        List<Movie> suggestedMovies = new ArrayList<>();
        Set<String> searchKeywords = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Search parameter:")) {
                    String keyword = line.substring(line.indexOf("Search parameter:") + 17).trim();
                    searchKeywords.add(keyword.toLowerCase());
                }
            }
        } catch (IOException ex) {
            log.error("Error reading log file: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).body(Collections.emptyList());
        }

        suggestedMovies = movieDatabase.values().stream()
                .filter(movie -> searchKeywords.stream().anyMatch(keyword -> movie.getTitle().toLowerCase().contains(keyword)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(suggestedMovies);
    }

    @PostMapping("/{id}/upload-poster")
    public ResponseEntity<String> uploadPoster(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Movie movie = movieDatabase.get(id);
            if (movie == null) {
                log.error("Movie with ID {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
            }
            
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            movie.setPoster(filePath);
            log.info("Poster uploaded successfully for movie '{}'. File: {}", movie.getTitle(), file.getOriginalFilename());

            return ResponseEntity.status(HttpStatus.OK).body("Poster uploaded successfully");
        } catch (IOException ex) {
            log.error("Error occurred while uploading poster: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload poster");
        }
    }
}