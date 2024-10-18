package org.example.touristguidedel3.repository;

import org.example.touristguidedel3.model.TouristAttraction;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TouristRepository {

    // Database connection configurations
    private String url = System.getenv("DB_URL");
    private String user = System.getenv("username");
    private String password = System.getenv("password");

    // Establishing the database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Create a new attraction in the database
    public void addTouristAttraction(TouristAttraction attraction) {
        String query = "INSERT INTO TOURISTATTRACTION (ATTRACTIONNAME, ATTRACTIONDISTRICT, ATTRACTIONCITY, ATTRACTIONDESC, ATTRACTIONTAGS) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, attraction.getName());
            stmt.setString(2, attraction.getDistrict());
            stmt.setString(3, attraction.getCity());
            stmt.setString(4, attraction.getDescription());
            stmt.setString(5, String.join(",", attraction.getTags()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int attractionNo = generatedKeys.getInt(1);
                    System.out.println("Inserted Attraction ID: " + attractionNo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read (R) all attractions from the database
    public List<TouristAttraction> getAllAttractions() {
        List<TouristAttraction> attractions = new LinkedList<>();
        String query = "SELECT * FROM TOURISTATTRACTION";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("ATTRACTIONNAME");
                String district = rs.getString("ATTRACTIONDISTRICT"); // Retrieve district
                String city = rs.getString("ATTRACTIONCITY");
                String description = rs.getString("ATTRACTIONDESC");
                String tags = rs.getString("ATTRACTIONTAGS");

                List<String> tagsList = Arrays.asList(tags.split(","));
                TouristAttraction attraction = new TouristAttraction(name, description, district, tagsList, city);
                attractions.add(attraction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    public List<TouristAttraction> getSpecificAttractionsByTags(String tag) {
        List<TouristAttraction> attractions = new LinkedList<>();
        String query = "SELECT * FROM TOURISTATTRACTION WHERE ATTRACTIONTAGS LIKE ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + tag + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("ATTRACTIONNAME");
                    String district = rs.getString("ATTRACTIONDISTRICT");
                    String city = rs.getString("ATTRACTIONCITY");
                    String description = rs.getString("ATTRACTIONDESC");
                    String tags = rs.getString("ATTRACTIONTAGS");

                    List<String> tagsList = Arrays.asList(tags.split(","));
                    TouristAttraction attraction = new TouristAttraction(name, description, district, tagsList, city);
                    attractions.add(attraction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    // Update attraction (U)
    public void updateTouristAttraction(int attractionNo) {
      /* in order to call: TouristRepository repository = new TouristRepository();
                           repository.updateTouristRepository( int attractionNo);
        */
        String query = "UPDATE TOURISTATTRACTION SET ATTRACTIONNAME=?, ATTRACTIONDISTRICT=?, ATTRACTIONCITY=?, ATTRACTIONDESC=?, ATTRACTIONTAGS=? WHERE ATTRACTIONNO=?";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the new attraction name: ");
        String newAttractionName = scanner.nextLine();

        System.out.println("Enter the new district: ");
        String newDistrict = scanner.nextLine();

        System.out.println("Enter the new city: ");
        String newCity = scanner.nextLine();

        System.out.println("Enter the new description: ");
        String newDescription = scanner.nextLine();

        System.out.println("Enter the new tags (comma-separated): ");
        String tagsInput = scanner.nextLine();
        List<String> newTags = Arrays.asList(tagsInput.split(","));

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set values for the prepared statement
            stmt.setString(1, newAttractionName);
            stmt.setString(2, newDistrict);
            stmt.setString(3, newCity);
            stmt.setString(4, newDescription);
            stmt.setString(5, String.join(",", newTags)); // The tags have to be a single string on the touristattraction table
            stmt.setInt(6, attractionNo); // The attraction number remains unchanged

            stmt.executeUpdate();
            System.out.println("Attraction updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    // Delete attraction (D)
    public void deleteTouristAttraction() {
        Scanner scanner = new Scanner(System.in);
        int attractionNo = -1;

        // Validate user input to ensure it's an integer
        while (true) {
            System.out.println("Which tourist attraction number do you want to delete?");

            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                attractionNo = scanner.nextInt();
                break;  // Exit loop if valid integer is provided
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the attraction number.");
                scanner.next();  // Consume the invalid input
            }
        }

        String query = "DELETE FROM TOURISTATTRACTION WHERE ATTRACTIONNO=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Use the valid attraction number in the query
            stmt.setInt(1, attractionNo);
            stmt.executeUpdate();

            System.out.println("Attraction with number " + attractionNo + " has been deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Close the scanner after use
        }
    }

}
