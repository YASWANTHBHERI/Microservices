package com.microservices.UserService.controllers;

import com.microservices.UserService.entities.User;
import com.microservices.UserService.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void>deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

}
