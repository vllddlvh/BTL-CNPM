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
    SeatScheduleResponse toSeatSchedule(SeatSchedule seatSchedule);

    @Mapping(source = "schedule.scheduleId", target = "scheduleId")
    @Mapping(source = "seat.seatId", target = "seatId")
    List<SeatScheduleResponse> toListSeatSchedule(List<SeatSchedule> listSeatSchedule);

    @Mapping(source = "scheduleId", target = "schedule.scheduleId")
    @Mapping(source = "seatId", target = "seat.seatId")
    SeatSchedule toCreateSeatSchedule(SeatScheduleRequest seatScheduleCreateRequest);
}
