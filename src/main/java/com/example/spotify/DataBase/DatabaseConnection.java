package com.example.spotify.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;

public class DatabaseConnection {

    private Connection connection;
    private Statement statement;

        public static Connection connectPlz() throws SQLException{

            String url = "jdbc:sqlite:spotifyDB.db";
            Connection conn = null;
            try {
                Connection connection = DriverManager.getConnection(url);
                return connection;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
    }