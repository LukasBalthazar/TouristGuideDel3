package org.example.touristguidedel3.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TouristRepository {

    public String url ="url;
    public String user = "username";
    public String password ="password";

    // Establishing the database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //Create a new attraction in the database



}
