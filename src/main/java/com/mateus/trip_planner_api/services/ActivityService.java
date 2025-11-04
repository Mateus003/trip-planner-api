package com.mateus.trip_planner_api.services;

import com.mateus.trip_planner_api.dto.ActivityDTO;
import com.mateus.trip_planner_api.dto.ActivityTripSumDTO;
import com.mateus.trip_planner_api.dto.DestinationDTO;
import com.mateus.trip_planner_api.models.ActivityModel;
import com.mateus.trip_planner_api.models.DestinationModel;
import com.mateus.trip_planner_api.repository.ActivityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Transactional
    public void create(ActivityDTO  activityDTO) {
        ActivityModel activityModel = new ActivityModel();

        activityModel.setTitle(activityDTO.getTitle());
        activityModel.setDescription(activityDTO.getDescription());
        activityModel.setCost(activityDTO.getCost());
        activityRepository.save(activityModel);

    }

    public List<ActivityModel> findAll() {
        return activityRepository.findAll();
    }

    public ActivityModel findById(UUID id) {
        return activityRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Atividade não encontrada"));
    }

    @Transactional
    public void delete(UUID id) {
        activityRepository.deleteById(id);
    }

    @Transactional
    public void update(UUID id, ActivityDTO activityDTO) {
        ActivityModel activityModel = findById(id);
        activityModel.setTitle(activityDTO.getTitle());
        activityModel.setDescription(activityDTO.getDescription());
        activityModel.setCost(activityDTO.getCost());
        activityRepository.save(activityModel);
    }

    public List<ActivityTripSumDTO> findByTripId(UUID tripId) {
        return activityRepository.findByTripId(tripId);
    }


}
