package com.mateus.trip_planner_api.controllers;

import com.mateus.trip_planner_api.dto.DestinationDTO;
import com.mateus.trip_planner_api.models.DestinationModel;
import com.mateus.trip_planner_api.services.DestinationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/destinations")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public ResponseEntity<List<DestinationModel>> getAll() {
        return ResponseEntity.ok(destinationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationModel> getById(@PathVariable UUID id) {
        return  ResponseEntity.ok(destinationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid DestinationDTO destinationDTO) {
        destinationService.save(destinationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Destino adicionado com sucesso");
    }

    @GetMapping("/city")
    public ResponseEntity<List<DestinationModel>> getAllByCity(@RequestParam @Valid String value) {
        return  ResponseEntity.status(HttpStatus.OK).body(destinationService.findByCity(value));
    }

    @GetMapping("/country")
    public ResponseEntity<List<DestinationModel>> getAllByCountry(@RequestParam @Valid String country) {
        return  ResponseEntity.status(HttpStatus.OK).body(destinationService.findByCountry(country));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> update(@PathVariable String id, @RequestBody @Valid  DestinationDTO destinationDTO) {
        destinationService.update(UUID.fromString(id), destinationDTO);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Destino atualizado com sucesso");
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        destinationService.deleteById(UUID.fromString(id));
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
