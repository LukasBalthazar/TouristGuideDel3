package org.example.touristguidedel3.repository;

import org.example.touristguidedel3.model.TouristAttraction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class TouristRepository {

    //see edit configurations
    private String url = System.getenv("DB_URL");
    private String user = System.getenv("username");
    private String password = System.getenv("password");

    // Establishing the database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //Create a new attraction in the database
    public void addTouristAttraction(TouristAttraction attraction) {
        //question mark ? is a placeholder. TO-DO: add a method to designate the actual values
        // remember the index parameters 1, 2, 3, 4, 5
        String query = "INSERT INTO TOURISTATTRACTION (ATTRACTIONNAME, ATTRACTIONDISTRICT, ATTRACTIONCITY, ATTRACTIONDESC, ATTRACTIONTAGS) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // insert an attraction into the database using the 5 parameters in the create table SQL constructor
            stmt.setString(1, attraction.getName()); //index 1
            stmt.setString(2, attraction.getDistrict()); //index 2
            stmt.setString(3, attraction.getCity()); //index 3
            stmt.setString(4, attraction.getDescription()); //index 4
            stmt.setString(5, String.join(",", attraction.getTags())); //index 5

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) { //.next basicly takes the next incremented index
                    int attractionNo = generatedKeys.getInt(1); // This will give you the auto-generated ATTRACTIONNO
                    System.out.println("Inserted Attraction ID: " + attractionNo);
                }
    }
     catch (SQLException e) {
         e.printStackTrace();
            }

            // read all attractions from the database
            public List<TouristAttraction> getAllAttractions() {
                List<TouristAttraction> attractions = new LinkedList<>();
                String query = "SELECT * FROM TOURISTATTRACTION"; //SQL command

                try (Connection conn = connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {

                    while (rs.next()) { //rs means result set

                        String name = rs.getString("ATTRACTIONNAME");
                        String district = rs.getString("ATTRACTIONDISTRICT");
                        String city = rs.getString("ATTRACTIONCITY");
                        String description = rs.getString("ATTRACTIONDESC");
                        String tags = rs.getString("ATTRACTIONTAGS");

                        List<String> tagsList = Arrays.asList(tags.split(","))

                        TouristAttraction attraction = new TouristAttraction(name, district, city, description, tagsList);
                        attractions.add(attraction);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return attractions;
            }
            }

            //update attraction (U)

}
