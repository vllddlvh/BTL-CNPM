package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.SeatRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.SeatResponse;
import com.cinemaweb.API.Cinema.Web.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<SeatResponse> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/{seatId}")
    public SeatResponse getSeat(@PathVariable String seatId) {
        return seatService.getSeat(seatId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createSeat(@RequestBody @Valid SeatRequest seatCreateRequest) {
        seatService.createSeat(seatCreateRequest);
        return "Creation seat finish!";
    }

    @PutMapping("/{seatId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateSeat(@RequestBody @Valid SeatRequest seatUpdateRequest, @PathVariable String seatId) {
        seatService.updateSeat(seatId, seatUpdateRequest);
        return "Seat with id " + seatId + " has been updating!";
    }

    @DeleteMapping("/{seatId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSeat(@PathVariable String seatId) {
        seatService.deleteSeat(seatId);
        return "Seat with id " + seatId + " has been delete!";
    }
}
