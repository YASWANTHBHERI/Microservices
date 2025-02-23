package com.microservices.UserService.services;

import com.microservices.UserService.entities.User;

import java.util.List;

public interface UserService {

    //create
    User createUser(User user);

    //get all users
    List<User> getAllUsers();

    //get by id

    User getUserById(String userId);

    //delete
    void deleteUserById(String userId);


}
