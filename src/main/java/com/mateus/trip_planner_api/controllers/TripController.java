package com.mateus.trip_planner_api.controllers;

import com.mateus.trip_planner_api.dto.*;
import com.mateus.trip_planner_api.models.ActivityModel;
import com.mateus.trip_planner_api.models.TripModel;
import com.mateus.trip_planner_api.services.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<String> createTrip(@RequestBody @Valid TripDTO tripDTO) {
        tripService.create(tripDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Viagem criada com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<TripModel>> getAllTrips() {
        return ResponseEntity.ok().body(tripService.getAllTrips());
    }

    @PostMapping("/participants")
    public ResponseEntity<String> addUserToTrip(@RequestBody @Valid UserTripDTO UserTripDTO) {
        tripService.addUserToTrip(UserTripDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sucesso ao adicionar usuário à viagem");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTrip(@PathVariable String id, @RequestBody @Valid TripDTO tripDTO) {
        tripService.updateTrip(UUID.fromString(id), tripDTO);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}/participants/{userId}")
    public ResponseEntity<String> deleteUserFromTrip( @PathVariable String id, @PathVariable String userId) {
        tripService.deleteUserTrip(UUID.fromString(id), UUID.fromString(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<UserTripSumDTO>> getParticipants(@PathVariable String id) {
        List<UserTripSumDTO> participants = tripService.findUserByTripId(UUID.fromString(id));

        return ResponseEntity.ok().body(participants);
    }

    @GetMapping("/{id}/destinations")
    public ResponseEntity<List<TripDestinationSumDTO>> getDestinationsByTrip(@PathVariable String id) {
        return ResponseEntity.ok().body(tripService.findDestinationsByTripId(UUID.fromString(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripModel> getTrip(@PathVariable String id) {
        TripModel trip = tripService.findTripBydId(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body(trip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable String id) {
         tripService.deleteTrip(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body("Viagem excluída com sucesso");
    }

    @PostMapping("/destinations")
    public ResponseEntity<String> addDestinationToTrip(@RequestBody @Valid TripDestinationDTO tripDestinationDTO) {
        tripService.addDestinationToTrip(tripDestinationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Destino adicionado à viagem com sucesso");
    }










}
