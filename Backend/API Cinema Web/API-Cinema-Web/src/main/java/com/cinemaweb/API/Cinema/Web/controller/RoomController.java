package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.RoomRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.RoomResponse;
import com.cinemaweb.API.Cinema.Web.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<RoomResponse> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{roomId}")
    public RoomResponse getRoom(@PathVariable String roomId) {
        return roomService.getRoom(roomId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createRoom(@RequestBody @Valid RoomRequest roomCreateRequest) {
        roomService.createRoom(roomCreateRequest);
        return "creation finish";
    }

    @PutMapping("/{roomId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRoom(@RequestBody @Valid RoomRequest roomUpdateRequest, @PathVariable String roomId) {
        roomService.updateRoom(roomId, roomUpdateRequest);
        return "Room with id " + roomId + " has been updating";
    }

    @DeleteMapping("/{roomId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteRoom(@PathVariable String roomId) {
        roomService.deleteRoom(roomId);

        return "Room with id " + roomId + " had been delete!";
    }
}
