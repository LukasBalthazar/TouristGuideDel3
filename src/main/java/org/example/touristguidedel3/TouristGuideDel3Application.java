package org.example.touristguidedel3;

import org.example.touristguidedel3.model.TouristAttraction;
import org.example.touristguidedel3.repository.TouristRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TouristGuideDel3Application {

    public static void main(String[] args) {
        SpringApplication.run(TouristGuideDel3Application.class, args);

        // Initialize the repository and fetch all attractions
        TouristRepository repository = new TouristRepository();
        List<TouristAttraction> attractions = repository.getAllAttractions();

        // Print each attraction retrieved from the database
        for (TouristAttraction attraction : attractions) {
            System.out.println(attraction);
        }

    }

}





