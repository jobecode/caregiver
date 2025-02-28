package es.carapapa.caregiver.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@Document(collection = "observations")
public class Observation {
    @Id
    private String id;
    private LocalDate date;
    private String comment;
    private String caregiverId;

    public Observation() {
    }

    public Observation(LocalDate date, String comment, String caregiverId) {
        this.date = date;
        this.comment = comment;
        this.caregiverId = caregiverId;
    }

}

