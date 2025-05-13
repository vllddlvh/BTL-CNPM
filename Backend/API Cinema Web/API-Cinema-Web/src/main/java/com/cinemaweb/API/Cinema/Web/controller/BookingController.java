package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.BookingRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.BookingResponse;
import com.cinemaweb.API.Cinema.Web.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingId}")
    public BookingResponse getBooking(@PathVariable String bookingId) {
        return bookingService.getBooking(bookingId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createBooking(@RequestBody @Valid BookingRequest bookingRequest) {
        bookingService.createBooking(bookingRequest);
        return "Creation booking finished";
    }
}
