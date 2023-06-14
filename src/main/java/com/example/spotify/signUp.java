package com.example.spotify;
import com.example.spotify.DataBase.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import com.example.spotify.DataBase.User;
import java.util.Random;
import java.sql.Connection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class signUp {
    public static Connection conn;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Button signUp;

    static {
        try {
            conn = DatabaseConnection.connectPlz();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void email(ActionEvent event) {

    }

    @FXML
    void password(ActionEvent event) {

    }

    @FXML
    void hadAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void signUp(ActionEvent event) throws IOException, SQLException {

        User user = new User(username.getText(),password.getText(),email.getText());
        RandomUserIDGenerator(user);
        Connection conn = DatabaseConnection.connectPlz();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(userID, username, password, emailAddress) VALUES (?,?,?,?)");
        //DatabaseConnection database= new DatabaseConnection();

        stmt.setString(1, user.getUserID());
        stmt.setString(2, user.getUsername());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getEmailAddress());
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }

        // Get a reference to the current stage
        Stage currentStage = (Stage) signUp.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void RandomUserIDGenerator(User user) {
        int maxRandomNumber = 10000;

        Random random = new Random();
        int randomNumber = random.nextInt(maxRandomNumber);

        String userID = "@"+user.getUsername() + randomNumber;
        user.setUserID(userID);


    }



}

