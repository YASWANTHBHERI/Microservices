package com.microservices.RatingService.controllers;

import com.microservices.RatingService.entities.Rating;
import com.microservices.RatingService.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;
    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    private Logger logger = LoggerFactory.getLogger(RatingController.class);
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }
    @GetMapping
    public ResponseEntity<List<Rating>>getAllRatings(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>>getAllRatingsByUserId(@PathVariable String userId){
        logger.info("ratings/users/userId called {}",userId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>>getAllRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByHotelId(hotelId));
    }

    @DeleteMapping("/user/{hotelId}")
    public ResponseEntity<Void>deleteRatingsByUserId(@PathVariable String userId){
        ratingService.deleteRatingByUserId(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/hotel/{hotelId}")
    public ResponseEntity<Void>deleteRatingByHotelId(@PathVariable String hotelId){
        ratingService.deleteRatingByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void>deleteRatingByRatingId(@PathVariable String ratingId){
        ratingService.deleteRatingByRatingId(ratingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
