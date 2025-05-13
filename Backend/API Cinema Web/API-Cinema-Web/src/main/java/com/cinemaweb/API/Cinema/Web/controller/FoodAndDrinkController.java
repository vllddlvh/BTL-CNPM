package com.cinemaweb.API.Cinema.Web.controller;

import com.cinemaweb.API.Cinema.Web.dto.request.FoodAndDrinkRequest;
import com.cinemaweb.API.Cinema.Web.dto.response.FoodAndDrinkResponse;
import com.cinemaweb.API.Cinema.Web.service.FoodAndDrinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodanddrink")
public class FoodAndDrinkController {
    @Autowired
    private FoodAndDrinkService foodAndDrinkService;

    @GetMapping
    public List<FoodAndDrinkResponse> getAllFoodAndDrink() {
        return foodAndDrinkService.getAllFoodAndDrink();
    }

    @GetMapping("/{id}")
    public FoodAndDrinkResponse getFoodAndDrink(@PathVariable String id) {
        return foodAndDrinkService.getFoodAndDrink(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createFoodAndDrink(@RequestBody @Valid FoodAndDrinkRequest fdCreateRequest) {
        foodAndDrinkService.createFoodAndDrink(fdCreateRequest);
        return "Creation fd finish";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateFoodAndDrink(@RequestBody @Valid FoodAndDrinkRequest fdUpdateRequest, @PathVariable String id) {
        foodAndDrinkService.updateFoodAndDrink(id, fdUpdateRequest);
        return "fd with id " + id + " has been updating";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteFoodAndDrink(@PathVariable String id) {
        foodAndDrinkService.deleteFoodAndDrink(id);
        return "fd with id " + id + " has been delete";
    }
}
