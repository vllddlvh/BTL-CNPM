package com.cinemaweb.API.Cinema.Web.dto.response;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    int bookingId;
    String roomName;
    String cinemaName;
    String movieName;
    String seatType;
    String seatRow;
    int seatNumber;
    String username;
    String foodAndDrinkName;
    Double price;
    LocalDateTime bookingDay;
}
