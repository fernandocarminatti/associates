package com.associates.associates.controller;

import com.associates.associates.model.UserModel;
import com.associates.associates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<UserModel> getAllUsers() {
         return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Integer id) {
        UserModel user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);

    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        UserModel savedUser = userService.createUser(user);
        URI uri = URI.create("/api/v1/users/" + savedUser.getUserId());
        return ResponseEntity.created(uri).body(savedUser);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Integer id, @RequestBody UserModel user) {
        Optional<UserModel> updatedUser = userService.patchUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser.get());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
