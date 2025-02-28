package es.carapapa.caregiver.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Date;

@Setter
@Getter
@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private Date start;
    private Date end;
    private List<String> caregivers;
    private String notes;
}
