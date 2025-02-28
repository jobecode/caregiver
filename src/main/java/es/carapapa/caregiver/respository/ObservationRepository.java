package es.carapapa.caregiver.respository;

import es.carapapa.caregiver.model.Observation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface ObservationRepository extends MongoRepository<Observation, String> {
    Optional<Observation> findByDate(LocalDate date);
}
