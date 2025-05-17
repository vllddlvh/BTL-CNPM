package com.cinemaweb.API.Cinema.Web.service;

import com.cinemaweb.API.Cinema.Web.repository.BookingFoodAndDrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingFoodAndDrinkService {
    @Autowired
    private BookingFoodAndDrinkRepository bookingFoodAndDrinkRepository;


}
