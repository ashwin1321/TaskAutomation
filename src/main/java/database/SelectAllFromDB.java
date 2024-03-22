package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectAllFromDB {

    public List<Object[]> selectFromDb(Connection connection){
        List<Object[]> rows = new ArrayList<>();

            if (connection != null) {
                String selectQuery = "SELECT * FROM user";

                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");

                        // Create an array containing the data for the current row
                        Object[] row = {id, username, password};
                        rows.add(row);
                    }
                } catch (SQLException e) {
                    System.err.println("Exception occurred during query execution: " + e.getMessage());
                }
            }

        return rows;
    }
}
