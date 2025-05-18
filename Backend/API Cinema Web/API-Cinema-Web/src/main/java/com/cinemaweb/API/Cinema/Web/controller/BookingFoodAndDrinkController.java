package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.BookingFoodAndDrinkRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.BookingFoodAndDrinkResponse;
import com.cinemaweb.API.Cinema.Web.service.BookingFoodAndDrinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookingFoodAndDrink")
public class BookingFoodAndDrinkController {
    @Autowired
    private BookingFoodAndDrinkService bookingFoodAndDrinkService;

    @GetMapping
    public List<BookingFoodAndDrinkResponse> getListBookingFoodAndDrinks() {
        return bookingFoodAndDrinkService.getListBookingFoodAndDrink();
    }

    @GetMapping("/{bookingFoodAndDrinkId}")
    public BookingFoodAndDrinkResponse getBookingFoodAndDrink(@PathVariable String bookingFoodAndDrinkId) {
        return bookingFoodAndDrinkService.getBookingFoodAndDrink(bookingFoodAndDrinkId);
    }

    @PostMapping
    public String createBookingFoodAndDrink(@RequestBody @Valid BookingFoodAndDrinkRequest bookingFoodAndDrinkCreateRequest) {
        bookingFoodAndDrinkService.createBookingFoodAndDrink(bookingFoodAndDrinkCreateRequest);

        return "Create bookingF&D finish!";
    }
}
