package org.example.touristguidedel3.repository;

import org.example.touristguidedel3.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TouristRepository {

    // Database connection configurations
    private String url = System.getenv("DB_url");
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
                String district = rs.getString("ATTRACTIONDISTRICT");
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

    // Get a specific attraction by its ID
    public TouristAttraction getAttractionById(int attractionNo) {
        TouristAttraction attraction = null;
        String query = "SELECT * FROM TOURISTATTRACTION WHERE ATTRACTIONNO=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, attractionNo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("ATTRACTIONNAME");
                    String district = rs.getString("ATTRACTIONDISTRICT");
                    String city = rs.getString("ATTRACTIONCITY");
                    String description = rs.getString("ATTRACTIONDESC");
                    String tags = rs.getString("ATTRACTIONTAGS");

                    List<String> tagsList = Arrays.asList(tags.split(","));
                    attraction = new TouristAttraction(name, description, district, tagsList, city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attraction;
    }


    // Update attraction (U)
    public void updateTouristAttraction(int attractionNo, TouristAttraction touristAttraction) {
        String query = "UPDATE TOURISTATTRACTION SET ATTRACTIONNAME=?, ATTRACTIONDISTRICT=?, ATTRACTIONCITY=?, ATTRACTIONDESC=?, ATTRACTIONTAGS=? WHERE ATTRACTIONNO=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set values for the prepared statement
            stmt.setString(1, touristAttraction.getName());
            stmt.setString(2, touristAttraction.getDistrict());
            stmt.setString(3, touristAttraction.getCity());
            stmt.setString(4, touristAttraction.getDescription());
            stmt.setString(5, String.join(",", touristAttraction.getTags()));
            stmt.setInt(6, attractionNo); // This is the attraction number

            stmt.executeUpdate();
            System.out.println("Attraction updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete attraction (D)
    public void deleteTouristAttraction(int attractionNo) {
        String query = "DELETE FROM TOURISTATTRACTION WHERE ATTRACTIONNO=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the attraction number in the query
            stmt.setInt(1, attractionNo);
            stmt.executeUpdate();

            System.out.println("Attraction with number " + attractionNo + " has been deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}