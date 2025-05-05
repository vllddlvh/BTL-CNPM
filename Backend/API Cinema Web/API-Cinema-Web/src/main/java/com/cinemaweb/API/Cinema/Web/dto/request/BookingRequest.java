package com.cinemaweb.API.Cinema.Web.dto.request;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
    int foodAndDrinkId;
    LocalDateTime bookingDay = LocalDateTime.now();
}
