package com.mateus.trip_planner_api.dto;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ActivityTripSumDTO {
    private UUID id;

    private String title;

    private String description;

    private LocalDateTime dateTime;

    private BigDecimal cost;

    private UUID trip_id;

    ActivityTripSumDTO(UUID id, String title, String description, LocalDateTime dateTime, BigDecimal cost, UUID trip_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.cost = cost;
        this.trip_id = trip_id;
    }
}
