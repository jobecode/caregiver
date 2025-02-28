package es.carapapa.caregiver.controller;


import es.carapapa.caregiver.model.Observation;
import es.carapapa.caregiver.respository.ObservationRepository;
import es.carapapa.caregiver.respository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/api/observations")
//@CrossOrigin(origins = "http://localhost:5173") // Permitir llamadas desde el frontend
public class ObservationController {

    private final ObservationRepository observationRepository;
    private final UserRepository userRepository;

    public ObservationController(ObservationRepository observationRepository, UserRepository userRepository) {
        this.observationRepository = observationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getObservationByDate(@RequestParam() String date) {
        if (date == null || date.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "La fecha es obligatoria y no puede estar vacía."));
        }

        try {
            LocalDate localDate = LocalDate.parse(date);
            Optional<Observation> observation = observationRepository.findByDate(localDate);

            if (observation.isPresent()) {
                return ResponseEntity.ok(observation.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No se encontró una observación para la fecha: " + date));
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Formato de fecha inválido. Usa YYYY-MM-DD."));
        }
    }




    @PostMapping
    public ResponseEntity<?> saveObservation(@RequestBody Observation observation) {
        LocalDate date = observation.getDate();

        if(userRepository.findByUsername(observation.getCaregiverId()).isEmpty()) {
            return ResponseEntity.badRequest().body("El cuidador no existe");
        }

        if(observationRepository.findByDate(date).isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe una observación para la fecha " + date);
        }

        observationRepository.save(observation);
        return ResponseEntity.ok(observation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateObservation(@PathVariable String id, @RequestBody Observation updatedObservation) {
        Optional<Observation> existingObservation = observationRepository.findById(id);

        if(existingObservation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if(userRepository.findByUsername(updatedObservation.getCaregiverId()).isEmpty()) {
            return ResponseEntity.badRequest().body("El cuidador no existe");
        }

        Observation observation = existingObservation.get();
        observation.setComment(updatedObservation.getComment());
        observation.setCaregiverId(updatedObservation.getCaregiverId());

        observationRepository.save(observation);
        return ResponseEntity.ok(observation);



    }


}
