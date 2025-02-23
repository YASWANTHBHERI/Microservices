package com.microservices.HotelService.services;


import com.microservices.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel createHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotelById(String hotelId);
    void deleteHotelById(String hotelId);
}
