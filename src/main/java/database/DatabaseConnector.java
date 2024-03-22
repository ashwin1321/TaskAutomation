package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    public static void main(String[] args) {
        String url = "jdbc:sqlite::memory:";

        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Connected to the sqlite database");

                String createTable = "CREATE TABLE IF NOT EXISTS user (" +
                        "id INTEGER PRIMARY KEY," +
                        "username TEXT NOT NULL UNIQUE," +
                        "password TEXT NOT NULL)";

                // Create a table
                try (Statement statement = connection.createStatement()) {
                    statement.execute(createTable);
                    System.out.println("Table created successfully");

                    // insert into table
                    String userInfo = "Insert Into user(username, password) values " +
                            "('daraz1@gmail.com', 'dataz124')," +
                            "('daraz2@gmail.com', 'dataz124')," +
                            "('daraz3@gmail.com', 'dataz124')," +
                            "('daraz4@gmail.com', 'dataz124')";

                    statement.executeUpdate(userInfo);
                    System.out.println("Data inserted successfully...");
                } catch (SQLException e) {
                    System.err.println("Exception occurred: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }
}
