package com.sherwin.restaurant_review_platform.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sherwin.restaurant_review_platform.domain.entities.Restaurant;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {

}
