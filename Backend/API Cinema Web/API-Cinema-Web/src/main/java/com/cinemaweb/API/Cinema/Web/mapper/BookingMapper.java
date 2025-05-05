package com.cinemaweb.API.Cinema.Web.mapper;

import com.cinemaweb.API.Cinema.Web.dto.request.BookingRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.BookingResponse;
import com.cinemaweb.API.Cinema.Web.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(source = "schedule.movie.movieName",target = "movieName")
    @Mapping(source = "schedule.room.roomName",target = "roomName")
    @Mapping(source = "schedule.cinema.cinemaName",target = "cinemaName")
    @Mapping(source = "seat.seatNumber",target = "seatNumber")
    @Mapping(source = "seat.seatRow",target = "seatRow")
    @Mapping(source = "seat.seatType",target = "seatType")
    @Mapping(source = "user.username",target = "username")
    @Mapping(source = "foodAndDrink.foodAndDrinkName",target = "foodAndDrinkName")
    BookingResponse toBookingResponse(Booking booking);

    @Mapping(source = "scheduleId", target = "schedule.scheduleId")
    @Mapping(source = "seatId", target = "seat.seatId")
    @Mapping(source = "userId", target = "user.ID")
    @Mapping(source = "foodAndDrinkId", target = "foodAndDrink.foodAndDrinkId")
    Booking toCreationBooking(BookingRequest bookingRequest);
}
