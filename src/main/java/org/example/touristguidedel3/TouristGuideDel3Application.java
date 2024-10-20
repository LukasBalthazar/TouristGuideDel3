package org.example.touristguidedel3;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class TouristGuideDel3Application {

    public static void main(String[] args) {
        SpringApplication.run(TouristGuideDel3Application.class, args);
        testDatabaseConnection();
    }
    private static void testDatabaseConnection() {
        // Database connection configurations
        String url = System.getenv("DB_url");
        String user = System.getenv("username");
        String password = System.getenv("password");

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


}

