package com.cinemaweb.API.Cinema.Web.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingFoodAndDrinkResponse {
    int bookingId;
    int foodAndDrinkId;
    int quantity;
    double price;
}
