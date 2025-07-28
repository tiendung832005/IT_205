package com.data.session14.service;

import com.data.session14.model.entity.Movie;
import com.data.session14.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}