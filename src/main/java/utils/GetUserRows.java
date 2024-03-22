package utils;

import database.DatabaseConnector;
import database.SelectAllFromDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class GetUserRows {
    public static  List<Object[]> getUserRows() {

        SelectAllFromDB getUsers = new SelectAllFromDB();
        String url = "jdbc:sqlite::memory:";

        try (Connection connection = DriverManager.getConnection(url)) {
            DatabaseConnector.databaseConnector(connection);
            return getUsers.selectFromDb(connection);

        }catch (SQLException e){
            System.out.println("Something went wrong...");
        }
        return null;
    }
}