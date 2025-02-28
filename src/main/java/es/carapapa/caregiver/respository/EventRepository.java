package es.carapapa.caregiver.respository;

import es.carapapa.caregiver.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
