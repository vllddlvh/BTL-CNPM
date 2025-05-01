package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.CinemaRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.CinemaResponse;
import com.cinemaweb.API.Cinema.Web.entity.Cinema;
import com.cinemaweb.API.Cinema.Web.service.CinemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public List<CinemaResponse> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{cinemaId}")
    public CinemaResponse getCinema(@PathVariable String cinemaId) {
        return cinemaService.getCinema(cinemaId);
    }

    @PostMapping
    public String createCinema(@RequestBody @Valid CinemaRequest cinemaCreateRequest) {
        cinemaService.createCinema(cinemaCreateRequest);
        return "Cinema has created";
    }

    @PutMapping("/{cinemaId}")
    public String updateCinema(@RequestBody @Valid CinemaRequest cinemaUpdateRequest, @PathVariable String cinemaId) {
        cinemaService.updateCinema(cinemaId, cinemaUpdateRequest);
        return "Update finish";
    }

    @DeleteMapping("/{cinemaId}")
    public String deleteCinema(@PathVariable String cinemaId) {
        cinemaService.deleteCinema(cinemaId);
        return "Cinema with id " + cinemaId + " has been deleted!";
    }
}
