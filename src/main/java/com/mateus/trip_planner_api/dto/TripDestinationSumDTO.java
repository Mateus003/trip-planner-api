package com.mateus.trip_planner_api.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TripDestinationSumDTO {

    private UUID destination_id;
    private String city;
    private String country;
    private String description;


    public TripDestinationSumDTO(UUID destination_id, String city, String country, String description)
        {
        this.destination_id = destination_id;
        this.city = city;
        this.country = country;
        this.description = description;
    }
}
