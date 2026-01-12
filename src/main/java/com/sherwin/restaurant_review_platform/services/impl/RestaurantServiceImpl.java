package com.sherwin.restaurant_review_platform.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.exceptions.RestaurantException;
import com.sherwin.restaurant_review_platform.mappers.RestaurantMapper;
import com.sherwin.restaurant_review_platform.repositories.RestaurantRepository;
import com.sherwin.restaurant_review_platform.services.RestaurantService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        List<RestaurantDto> allRestaurantsDto = restaurantMapper.toListDto(allRestaurants);

        return allRestaurantsDto;
    }

    @Override
    public RestaurantDto createRestaurant(CreateRestaurantDto createRestaurantDto) {
        validateRestaurantInformation(createRestaurantDto);

        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName(createRestaurantDto.getName());
        newRestaurant.setCuisineType(createRestaurantDto.getCuisineType());
        newRestaurant.setPhoneNumber(createRestaurantDto.getPhoneNumber());
        newRestaurant.setAverageRating(0f);
        newRestaurant.setCreatedAt(LocalDateTime.now());
        newRestaurant.setUpdatedAt(LocalDateTime.now());
        newRestaurant.setAddress(createRestaurantDto.getAddress());
        newRestaurant.setOperatingHours(createRestaurantDto.getOperatingHours());

        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

        return restaurantMapper.toDto(savedRestaurant);
    }

    @Override
    public RestaurantDto getRestaurantById(UUID id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            throw new RestaurantException("Restaurant Not Found FOOL");
        }

        return restaurantMapper.toDto(restaurant.get());
    }

    private void validateRestaurantInformation(CreateRestaurantDto createRestaurantDto) {
        if (createRestaurantDto.getName().isBlank()) {
            throw new RestaurantException("Restaurant name can not be blank");
        } else if (createRestaurantDto.getCuisineType().isBlank()) {
            throw new RestaurantException("Restaurant cuisine type can not be blank");
        } else if (createRestaurantDto.getPhoneNumber().isBlank()) {
            throw new RestaurantException("Restaurant phone number can not be blank");
        }
    }

    @Override
    public RestaurantDto updateRestaurant(UUID id, CreateRestaurantDto createRestaurantDto) {
        validateRestaurantInformation(createRestaurantDto);

        Optional<Restaurant> restaurantOp = restaurantRepository.findById(id);
        if (restaurantOp.isEmpty()) {
            throw new RestaurantException("Restaurant Not Found");
        }

        Restaurant restaurant = restaurantOp.get();
        restaurant.setName(createRestaurantDto.getName());
        restaurant.setCuisineType(createRestaurantDto.getCuisineType());
        restaurant.setPhoneNumber(createRestaurantDto.getPhoneNumber());
        restaurant.setUpdatedAt(LocalDateTime.now());
        restaurant.setAddress(createRestaurantDto.getAddress());
        restaurant.setOperatingHours(createRestaurantDto.getOperatingHours());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        return restaurantMapper.toDto(updatedRestaurant);
    }

    @Override
    public void deleteRestaurant(UUID id) {
        Optional<Restaurant> restaurantOp = restaurantRepository.findById(id);
        if (restaurantOp.isEmpty()) {
            throw new RestaurantException("Restaurant Not Found");
        }
        restaurantRepository.delete(restaurantOp.get());
    }

    @Override
    public List<RestaurantDto> searchRestaurant(String keyword) {
        List<Restaurant> searchRestaurant = restaurantRepository.searchRestaurant(keyword);
        List<RestaurantDto> searchRestaurantDtos = restaurantMapper.toListDto(searchRestaurant);

        return searchRestaurantDtos;
    }

}
