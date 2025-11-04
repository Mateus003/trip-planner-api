package com.mateus.trip_planner_api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DestinationDTO {

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    private String description;

    private String imageUrl;
}
