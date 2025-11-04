package com.mateus.trip_planner_api.controllers;

import com.mateus.trip_planner_api.dto.UserDTO;
import com.mateus.trip_planner_api.models.UserModel;
import com.mateus.trip_planner_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll());
    }


    @GetMapping("/search/email")
    public ResponseEntity<UserModel> getByEmail(@RequestParam String value) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findByEmail(value));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(uuid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteById(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id,@RequestBody @Valid UserDTO userDTO) {
        userService.update(UUID.fromString(id), userDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<UserModel>> getByName(@RequestParam String name) {
        return  ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAllByName(name));
    }


}