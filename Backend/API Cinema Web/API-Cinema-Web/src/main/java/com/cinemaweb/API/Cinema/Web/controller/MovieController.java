package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.MovieRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.MovieResponse;
import com.cinemaweb.API.Cinema.Web.service.MovieService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieid}")
    public MovieResponse getMovie(@PathVariable String movieid) {
        return movieService.getMovie(movieid);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void createMovie(@RequestBody @Valid MovieRequest movieCreateRequest) {
        movieService.createMovie(movieCreateRequest);
    }

    @PutMapping("{movieid}")
    @PreAuthorize("hasRole('ADMIN')")
    public MovieResponse updateMovie(@PathVariable String movieid, @RequestBody MovieRequest movieUpdateRequest) {
        return movieService.updateMovie(movieid, movieUpdateRequest);
    }

    @DeleteMapping("{movieid}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMovie(@PathVariable String movieid) {
        movieService.deleteMovie(movieid);
        return "Film with id" + movieid + " has been deleted";
    }
}
