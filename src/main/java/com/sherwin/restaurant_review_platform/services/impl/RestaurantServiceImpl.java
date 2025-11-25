package com.sherwin.restaurant_review_platform.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherwin.restaurant_review_platform.domain.dtos.CreateRestaurantDto;
import com.sherwin.restaurant_review_platform.domain.dtos.RestaurantDto;
import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;
import com.sherwin.restaurant_review_platform.mappers.RestaurantMapper;
import com.sherwin.restaurant_review_platform.repositories.RestaurantRepository;
import com.sherwin.restaurant_review_platform.services.RestaurantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        List<RestaurantDto> allRestaurantsDto = allRestaurants
                .stream()
                .map(restaurantMapper::toDto)
                .toList();

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

        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

        return restaurantMapper.toDto(savedRestaurant);
    }

    @Override
    public RestaurantDto getRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            throw new RuntimeException("Restaurant Not Found FOOL");
        }

        return restaurantMapper.toDto(restaurant.get());
    }

    private void validateRestaurantInformation(CreateRestaurantDto createRestaurantDto) {
        if (createRestaurantDto.getName().isBlank()) {
            throw new RuntimeException("Restaurant name can not be blank");
        } else if (createRestaurantDto.getCuisineType().isBlank()) {
            throw new RuntimeException("Restaurant cuisine type can not be blank");
        } else if (createRestaurantDto.getPhoneNumber().isBlank()) {
            throw new RuntimeException("Restaurant phone number can not be blank");
        }
    }

    @Override
    public RestaurantDto updateRestaurant(Integer id, CreateRestaurantDto createRestaurantDto) {
        validateRestaurantInformation(createRestaurantDto);

        Optional<Restaurant> restaurantOp = restaurantRepository.findById(id);
        if (restaurantOp.isEmpty()) {
            throw new RuntimeException("Restaurant Not Found");
        }

        Restaurant restaurant = restaurantOp.get();
        restaurant.setName(createRestaurantDto.getName());
        restaurant.setCuisineType(createRestaurantDto.getCuisineType());
        restaurant.setPhoneNumber(createRestaurantDto.getPhoneNumber());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        return restaurantMapper.toDto(updatedRestaurant);
    }

    @Override
    public void deleteRestaurant(Integer id) {
        Optional<Restaurant> restaurantOp = restaurantRepository.findById(id);
        if (restaurantOp.isEmpty()) {
            throw new RuntimeException("Restaurant Not Found");
        }
        restaurantRepository.delete(restaurantOp.get());
    }

}
