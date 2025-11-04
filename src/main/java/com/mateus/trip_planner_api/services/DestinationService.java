package com.mateus.trip_planner_api.services;

import com.mateus.trip_planner_api.dto.DestinationDTO;
import com.mateus.trip_planner_api.models.DestinationModel;
import com.mateus.trip_planner_api.repository.DestinationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public List<DestinationModel> findAll() {
        return destinationRepository.findAll();
    }


    @Transactional
    public void save(DestinationDTO destinationDTO) {
        DestinationModel destinationModel = new DestinationModel();
        destinationModel.setCity(destinationDTO.getCity());
        destinationModel.setCountry(destinationDTO.getCountry());
        destinationModel.setDescription(destinationDTO.getDescription());
        destinationModel.setImageUrl(destinationDTO.getImageUrl());

        destinationRepository.save(destinationModel);
    }

    public DestinationModel findById(UUID id) {
        return destinationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Destino não encontrado"));
    }

    public List<DestinationModel> findByCity(String city) {
        return  destinationRepository.findByCity(city);
    }

    public List<DestinationModel> findByCountry(String country) {
        return  destinationRepository.findByCountry(country);
    }

    @Transactional
    public void deleteById(UUID id) {
        destinationRepository.deleteById(id);
    }

    @Transactional
    public void update(UUID id,DestinationDTO destinationDTO) {
        DestinationModel destinationModel = destinationRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Destino não encontrado")
                );
       destinationModel.setCity(destinationDTO.getCity());
       destinationModel.setCountry(destinationDTO.getCountry());
       destinationModel.setDescription(destinationDTO.getDescription());
       destinationModel.setImageUrl(destinationDTO.getImageUrl());

        destinationRepository.save(destinationModel);

    }


}
