package database;

import utils.GetProperties;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class DatabaseConnector {
    public static void main(String[] args) {
        String path = GetProperties.getProperty("db.path");
        String url = "jdbc:sqlite:" + path;

        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Connected to the database");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }
}