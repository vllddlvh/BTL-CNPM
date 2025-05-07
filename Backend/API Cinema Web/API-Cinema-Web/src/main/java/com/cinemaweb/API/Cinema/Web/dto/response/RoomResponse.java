package com.cinemaweb.API.Cinema.Web.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponse {
    int cinemaId;
    int foodAndDrinkId;
    int movieId;
    int roomId;
    String cinemaName;
    String roomName;
}
