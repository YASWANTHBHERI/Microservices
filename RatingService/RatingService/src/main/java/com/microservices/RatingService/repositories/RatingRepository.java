package com.microservices.RatingService.repositories;

import com.microservices.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating,String> {
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

    void deleteByUserId(String userId);
    void deleteByHotelId(String hotelId);
}
