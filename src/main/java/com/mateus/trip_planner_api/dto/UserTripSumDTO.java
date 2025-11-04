package com.mateus.trip_planner_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class UserTripSumDTO {
    private UUID tripId;
    private String userName;
    private String userEmail;

    public UserTripSumDTO(UUID tripId, String userName, String userEmail) {
        this.tripId = tripId;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
