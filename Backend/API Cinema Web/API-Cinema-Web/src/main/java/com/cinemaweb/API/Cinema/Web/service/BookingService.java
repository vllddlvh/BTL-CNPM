package com.cinemaweb.API.Cinema.Web.service;

import com.cinemaweb.API.Cinema.Web.dto.request.BookingRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.BookingResponse;
import com.cinemaweb.API.Cinema.Web.entity.Booking;
import com.cinemaweb.API.Cinema.Web.entity.BookingFoodAndDrink;
import com.cinemaweb.API.Cinema.Web.mapper.BookingMapper;
import com.cinemaweb.API.Cinema.Web.repository.BookingFoodAndDrinkRepository;
import com.cinemaweb.API.Cinema.Web.repository.BookingRepository;
import com.cinemaweb.API.Cinema.Web.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingFoodAndDrinkRepository bookingFoodAndDrinkRepository;

    public BookingResponse getBooking(String bookingId) {
        return bookingMapper.toBookingResponse(bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking id is not found")));
    }

    public void createBooking(BookingRequest bookingRequest) {
        Booking booking = bookingMapper.toCreationBooking(bookingRequest);
        double seatPrice = seatRepository.findById(Integer.toString(booking.getSeat().getSeatId()))
                .orElseThrow(() -> new RuntimeException("Seat in booking is not found")).getSeatPrice();

        double foodAndDrinksPrice = 0;
        if(bookingFoodAndDrinkRepository.existsByBookingId(booking.getBookingId())) {
            List<BookingFoodAndDrink> listBookingFoodAndDrink = bookingFoodAndDrinkRepository.
                    findByBooking_BookingId(booking.getBookingId());

            for(int i = 0; i < listBookingFoodAndDrink.size(); i++ ) {
                foodAndDrinksPrice += listBookingFoodAndDrink.get(i).getPrice();
            }
        }

        booking.setPrice(seatPrice + foodAndDrinksPrice);
        bookingRepository.save(booking);
    }
}
