package com.cinemaweb.API.Cinema.Web.service;

import com.cinemaweb.API.Cinema.Web.dto.request.BookingRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.BookingFoodAndDrinkResponse;
import com.cinemaweb.API.Cinema.Web.dto.response.BookingResponse;
import com.cinemaweb.API.Cinema.Web.dto.response.SeatResponse;
import com.cinemaweb.API.Cinema.Web.entity.*;
import com.cinemaweb.API.Cinema.Web.mapper.BookingFoodAndDrinkMapper;
import com.cinemaweb.API.Cinema.Web.mapper.BookingMapper;
import com.cinemaweb.API.Cinema.Web.mapper.BookingSeatMapper;
import com.cinemaweb.API.Cinema.Web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private BookingSeatRepository bookingSeatRepository;

    @Autowired
    private BookingFoodAndDrinkRepository bookingFoodAndDrinkRepository;

    @Autowired
    private BookingFoodAndDrinkMapper bookingFoodAndDrinkMapper;

    @Autowired
    private BookingSeatMapper bookingSeatMapper;

    @Autowired
    private SeatScheduleRepository seatScheduleRepository;

    public BookingResponse getBooking(String bookingId) {
        int bookingIdInt = Integer.parseInt(bookingId);
        List<BookingFoodAndDrinkResponse> listBookingFoodAndDrinks = null;
        if (bookingFoodAndDrinkRepository.existsByBooking_BookingId(bookingIdInt)) {
            listBookingFoodAndDrinks = bookingFoodAndDrinkMapper.toListBookingFoodAndDrinks(
                    bookingFoodAndDrinkRepository.findByBooking_BookingId(bookingIdInt));
        }
        BookingResponse booking = bookingMapper.toBookingResponse(bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking id is not found")));
        // Lấy về các seat của booking
        List<BookingSeat> seats = bookingSeatRepository.findAllByBooking_BookingId(bookingIdInt)
                .orElseThrow(() -> new RuntimeException("Invalid seats"));
        booking.setFoodAndDrinks(listBookingFoodAndDrinks);
        booking.setSeats(seats.stream().map(bookingSeatMapper::toBookingSeatResponse).toList());
        return booking;
    }

    public void createBooking(BookingRequest bookingRequest) {
        Booking booking = bookingMapper.toCreationBooking(bookingRequest);

        // Tinh tien seat
        
        double seatPrice = 0;
        List<BookingSeat> bookingSeats = bookingSeatRepository.findAllByBooking_BookingId(booking.getBookingId())
                .orElseThrow(() -> new RuntimeException("Invalid booking seat"));
        List<SeatSchedule> seatSchedules = new ArrayList<>();
        for (int i = 0; i < bookingSeats.size(); i++) {
            seatPrice += bookingSeats.get(i).getPrice();
            SeatSchedule seatSchedule =  bookingSeats.get(i).getSeatSchedule();
            seatSchedule.setSeatState(true);
            seatSchedules.add(seatSchedule);
        }
        seatScheduleRepository.saveAll(seatSchedules);

        double foodAndDrinksPrice = 0;
        if(bookingFoodAndDrinkRepository.existsByBooking_BookingId(booking.getBookingId())) {
            List<BookingFoodAndDrink> listBookingFoodAndDrink = bookingFoodAndDrinkRepository.
                    findByBooking_BookingId(booking.getBookingId());

            for (int i = 0; i < listBookingFoodAndDrink.size(); i++ ) {
                foodAndDrinksPrice += listBookingFoodAndDrink.get(i).getPrice();
            }
        }

        booking.setPrice(seatPrice + foodAndDrinksPrice);
        bookingRepository.save(booking);
    }
}
