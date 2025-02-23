package com.microservices.UserService.services.impl;

import com.microservices.UserService.entities.Hotel;
import com.microservices.UserService.entities.Rating;
import com.microservices.UserService.entities.User;
import com.microservices.UserService.exceptions.ResourceNotFoundException;
import com.microservices.UserService.repositories.UserRepository;
import com.microservices.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User createUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException());
        try {
            String ratingUrl = "http://RATING-SERVICE/ratings/users/" + user.getUserId();
            Rating[] ratingsOfUserArr = restTemplate.getForObject(ratingUrl, Rating[].class);

            List<Rating> ratingsOfUserList = Arrays.stream(ratingsOfUserArr).toList();
            logger.info("ratings list: {}", ratingsOfUserList);

            List<Rating> ratingsList = ratingsOfUserList.stream().map(rating -> {
                //api call to hotel service to get each hotel
                // set the hotel to rating
                //url = http://localhost:8082/hotel/{hotelId}
                String hotelUrl = "http://HOTEL-SERVICE/hotel/" + rating.getHotelId();
                Hotel hotel = restTemplate.getForObject(hotelUrl, Hotel.class);
                logger.info("calling to hotel service to get hotel {}", rating.getHotelId());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingsList);
        }
        catch (Exception e){
            e.printStackTrace();
            return user;
        }
        return user;
        // return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException());
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }
}
