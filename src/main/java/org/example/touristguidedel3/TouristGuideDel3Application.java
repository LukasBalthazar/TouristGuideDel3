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

        // Uncomment to test the database connection
        // testDatabaseConnection();
    }

    // Uncomment this method to test database connection
    /*
    private static void testDatabaseConnection() {
        // Database connection configurations
        String url = System.getenv("DB_url");
        String user = System.getenv("username");
        String password = System.getenv("password");

        System.out.println("DB URL: " + url);
        System.out.println("Username: " + user);
        System.out.println("Password: " + password);

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
    */
}





