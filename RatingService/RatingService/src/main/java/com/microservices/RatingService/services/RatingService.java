package com.microservices.RatingService.services;

import com.microservices.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getAllRatings();

    List<Rating> getRatingsByUserId(String userId);
    List<Rating> getRatingsByHotelId(String hotelId);

    void deleteRatingByHotelId(String hotelId);
    void deleteRatingByUserId(String userId);

    void deleteRatingByRatingId(String ratingId);
}
