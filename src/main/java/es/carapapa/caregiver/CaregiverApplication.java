package es.carapapa.caregiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "es.carapapa.caregiver")
public class CaregiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaregiverApplication.class, args);
    }

}
