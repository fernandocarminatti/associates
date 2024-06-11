package com.associates.associates.controller;

import com.associates.associates.exceptions.CpfException;
import com.associates.associates.exceptions.IdNotFoundException;
import com.associates.associates.model.UserModel;
import com.associates.associates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(CpfException.class)
    public ResponseEntity<String> handleCpfException(CpfException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
