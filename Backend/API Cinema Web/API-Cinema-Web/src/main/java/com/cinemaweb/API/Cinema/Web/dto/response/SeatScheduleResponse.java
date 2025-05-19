package com.cinemaweb.API.Cinema.Web.dto.response;

import com.cinemaweb.API.Cinema.Web.entity.Schedule;
import com.cinemaweb.API.Cinema.Web.entity.Seat;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatScheduleResponse {
    int seatScheduleId;
    int scheduleId;
    int seatId;
    boolean seatState; // true = booked, false = available
}
