package com.cinemaweb.API.Cinema.Web.mapper;

import com.cinemaweb.API.Cinema.Web.dto.request.SeatScheduleRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.SeatScheduleResponse;
import com.cinemaweb.API.Cinema.Web.entity.SeatSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatScheduleMapper {
    @Mapping(source = "schedule.scheduleId", target = "scheduleId")
    @Mapping(source = "seat.seatId", target = "seatId")
    @Mapping(source = "seat.seatType", target = "seatType")
    @Mapping(source = "seat.seatRow", target = "seatRow")
    @Mapping(source = "seat.seatNumber", target = "seatNumber")
    @Mapping(source = "seat.seatPrice", target = "seatPrice")
    SeatScheduleResponse toSeatSchedule(SeatSchedule seatSchedule);

    @Mapping(source = "schedule.scheduleId", target = "scheduleId")
    @Mapping(source = "seat.seatId", target = "seatId")
    @Mapping(source = "seat.seatType", target = "seatType")
    @Mapping(source = "seat.seatRow", target = "seatRow")
    @Mapping(source = "seat.seatNumber", target = "seatNumber")
    @Mapping(source = "seat.seatPrice", target = "seatPrice")
    List<SeatScheduleResponse> toListSeatSchedule(List<SeatSchedule> listSeatSchedule);

    @Mapping(source = "scheduleId", target = "schedule.scheduleId")
    @Mapping(source = "seatId", target = "seat.seatId")
    @Mapping(source = "seatType", target = "seat.seatType")
    @Mapping(source = "seatRow", target = "seat.seatRow")
    @Mapping(source = "seatNumber", target = "seat.seatNumber")
    @Mapping(source = "seatPrice", target = "seat.seatPrice")
    SeatSchedule toCreateSeatSchedule(SeatScheduleRequest seatScheduleCreateRequest);
}
