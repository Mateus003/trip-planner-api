package com.mateus.trip_planner_api.controllers;

import com.mateus.trip_planner_api.dto.ActivityDTO;
import com.mateus.trip_planner_api.dto.ActivityTripSumDTO;
import com.mateus.trip_planner_api.models.ActivityModel;
import com.mateus.trip_planner_api.repository.ActivityRepository;
import com.mateus.trip_planner_api.repository.DestinationRepository;
import com.mateus.trip_planner_api.services.ActivityService;
import com.mateus.trip_planner_api.services.DestinationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/activities")
public class ActivitiesController {
    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<Void> addActivity(@RequestBody @Valid ActivityDTO activityDTO) {
        activityService.create(activityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ActivityModel>> getAll() {
        List<ActivityModel> activities = activityService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityModel> getActivityById(@PathVariable("id") String id) {
        ActivityModel activity = activityService.findById(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body(activity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable("id") String id) {
        activityService.delete(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateActivity(@PathVariable("id") String id, @RequestBody @Valid ActivityDTO activityDTO) {
        activityService.update(UUID.fromString(id), activityDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/by-trip/{tripId}")
    public ResponseEntity<List<ActivityTripSumDTO>> getActivitiesByTrip(@PathVariable("tripId") String tripId) {
        List<ActivityTripSumDTO> activities= activityService.findByTripId(UUID.fromString(tripId));
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }


}
