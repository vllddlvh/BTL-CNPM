package com.cinemaweb.API.Cinema.Web.repository;

import com.cinemaweb.API.Cinema.Web.entity.BookingFoodAndDrink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingFoodAndDrinkRepository extends JpaRepository<BookingFoodAndDrink, String> {
    List<BookingFoodAndDrink> findByBooking_BookingId(int bookingId);

    boolean existsByBooking_BookingId(int bookingId);
}
