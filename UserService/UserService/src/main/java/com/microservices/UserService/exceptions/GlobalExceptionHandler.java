package com.microservices.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> resourceExceptionHandler(ResourceNotFoundException ex){
        Map<String,Object> body = new HashMap<>();
        body.put("message",ex.getMessage());
        body.put("error","not found");
        body.put("status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }
}
