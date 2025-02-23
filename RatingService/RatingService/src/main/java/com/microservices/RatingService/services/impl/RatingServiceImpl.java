package com.microservices.RatingService.services.impl;

import com.microservices.RatingService.entities.Rating;
import com.microservices.RatingService.repositories.RatingRepository;
import com.microservices.RatingService.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    public RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    private Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);
    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        logger.info("rating of user {}",ratingRepository.findByUserId(userId));
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public void deleteRatingByHotelId(String hotelId) {
        ratingRepository.deleteByHotelId(hotelId);
    }

    @Override
    public void deleteRatingByUserId(String userId) {
        ratingRepository.deleteByUserId(userId);
    }

    @Override
    public void deleteRatingByRatingId(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
