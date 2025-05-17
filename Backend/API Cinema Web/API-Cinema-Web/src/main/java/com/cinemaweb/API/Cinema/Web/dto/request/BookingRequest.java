package com.cinemaweb.API.Cinema.Web.dto.request;

import com.cinemaweb.API.Cinema.Web.entity.BookingFoodAndDrink;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    int scheduleId;
    int seatId;
    String userId;
    List<BookingFoodAndDrink> foodAndDrinks;
    LocalDateTime bookingDay = LocalDateTime.now();
}
