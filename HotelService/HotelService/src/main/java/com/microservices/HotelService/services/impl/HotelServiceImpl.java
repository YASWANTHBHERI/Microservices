package com.microservices.HotelService.services.impl;

import com.microservices.HotelService.entities.Hotel;
import com.microservices.HotelService.exceptions.ResourceNotFoundException;
import com.microservices.HotelService.repositories.HotelRepository;
import com.microservices.HotelService.services.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException());
    }

    @Override
    public void deleteHotelById(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
