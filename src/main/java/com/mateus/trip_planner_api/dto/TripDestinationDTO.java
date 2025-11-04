package com.mateus.trip_planner_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TripDestinationDTO {

    @NotNull
    private UUID trip_id;

    @NotNull
    private UUID destination_id;

}
