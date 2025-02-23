package com.microservices.HotelService.controller;

import com.microservices.HotelService.entities.Hotel;
import com.microservices.HotelService.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;
    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>>getAllHotels(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel>getHotelById(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
    }
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void>deleteHotelById(@PathVariable String hotelId){
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }


}
