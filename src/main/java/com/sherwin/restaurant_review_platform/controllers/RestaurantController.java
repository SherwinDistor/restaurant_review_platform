package com.sherwin.restaurant_review_platform.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;
import com.sherwin.restaurant_review_platform.services.RestaurantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> allRestaurants = restaurantService.getAllRestaurants();

        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody CreateRestaurantDto createRestaurantDto) {
        RestaurantDto createdRestaurant = restaurantService.createRestaurant(createRestaurantDto);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable UUID id) {
        RestaurantDto restaurant = restaurantService.getRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(
            @PathVariable UUID id,
            @RequestBody CreateRestaurantDto createRestaurantDto) {

        RestaurantDto updatedRestaurant = restaurantService.updateRestaurant(id, createRestaurantDto);

        return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
