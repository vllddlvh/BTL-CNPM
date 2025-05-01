package com.cinemaweb.API.Cinema.Web.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatRequest {
    int roomId;
    String seatType;
    String seatRow;
    int seatNumber;
    double seatPrice;

    boolean seatState = false; // 0 is not sold, 1 is sold
}
