package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.ScheduleRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.ScheduleResponse;
import com.cinemaweb.API.Cinema.Web.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public List<ScheduleResponse> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponse getSchedule(@PathVariable String scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createSchedule(@RequestBody @Valid ScheduleRequest scheduleCreate) {
        scheduleService.createSchedule(scheduleCreate);
        return "Creation schedule finish!";
    }

    @PutMapping("/{scheduleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateSchedule(@RequestBody @Valid ScheduleRequest scheduleUpdate, @PathVariable String scheduleId) {
        scheduleService.updateSchedule(scheduleId,scheduleUpdate);
        return "Update schedule with id " + scheduleId + " finish!";
    }

    @DeleteMapping("/{scheduleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSchedule(@PathVariable String scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return "delete schedule finish!";
    }
}
