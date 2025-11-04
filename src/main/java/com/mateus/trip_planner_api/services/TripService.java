package com.mateus.trip_planner_api.services;

import com.mateus.trip_planner_api.dto.*;
import com.mateus.trip_planner_api.models.DestinationModel;
import com.mateus.trip_planner_api.models.TripModel;
import com.mateus.trip_planner_api.models.UserModel;
import com.mateus.trip_planner_api.repository.DestinationRepository;
import com.mateus.trip_planner_api.repository.TripRepository;
import com.mateus.trip_planner_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DestinationRepository destinationRepository;


    @Transactional
    public void create(TripDTO tripDTO) {
        TripModel tripModel = new TripModel();
        tripModel.setTitle(tripDTO.getTitle());
        tripModel.setDescription(tripDTO.getDescription());

        tripModel.setStartDate(tripDTO.getStartDate());
        tripModel.setEndDate(tripDTO.getEndDate());

        tripRepository.save(tripModel);

    }

    @Transactional
    public void addUserToTrip(UserTripDTO userTripDTO) {
        TripModel trip = tripRepository.findById(userTripDTO.getTrip_id())
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada"));

        UserModel user = userRepository.findById(userTripDTO.getUser_id())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        trip.getUsers().add(user);
        tripRepository.save(trip);
    }

    public List<TripModel> getAllTrips() {
        return tripRepository.findAll();
    }

    @Transactional
    public void deleteTrip(UUID id) {
        TripModel trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada"));
        tripRepository.delete(trip);
    }

    @Transactional
    public void updateTrip(UUID uuid, TripDTO tripDTO) {
        TripModel tripModel =  tripRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada"));

        tripModel.setTitle(tripDTO.getTitle());
        tripModel.setDescription(tripDTO.getDescription());
        tripModel.setStartDate(tripDTO.getStartDate());
        tripModel.setEndDate(tripDTO.getEndDate());
    }

    public TripModel findTripBydId(UUID uuid) {
        return tripRepository.findById(uuid)
                .orElseThrow(()-> new EntityNotFoundException("Viagem não encontrada"));
    }

//    public List<UserTripDTO> getUserTrips(UUID uuid) {
//
//    }



    @Transactional
    public void deleteUserTrip(UUID tripId, UUID userId) {
        TripModel trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada"));

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        trip.getUsers().remove(user);
    }

    public List<UserTripSumDTO> findUserByTripId(UUID tripId) {
        return tripRepository.findUsersByTripId(tripId);
    }

    @Transactional
    public void addDestinationToTrip(TripDestinationDTO tripDestinationDTO){
        TripModel trip = tripRepository.findById(tripDestinationDTO.getTrip_id())
                .orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada"));

        DestinationModel destination = destinationRepository.findById(tripDestinationDTO.getDestination_id())
                .orElseThrow(() -> new EntityNotFoundException("Destino não encontrado"));

        trip.getDestinations().add(destination);
    }

    public List<TripDestinationSumDTO> findDestinationsByTripId(UUID tripId) {
        return tripRepository.findDestinationsByTripId(tripId);
    }


}
