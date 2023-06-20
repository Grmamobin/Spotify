package com.example.spotify;
import com.example.spotify.DataBase.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.spotify.DataBase.User;

import java.net.Socket;
import java.sql.Connection;
import com.google.gson.JsonObject;
import com.example.spotify.Handler.Request;
import java.io.IOException;
import java.sql.SQLException;
import com.google.gson.Gson;
import java.util.Scanner;

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
    private TextField userId;
    @FXML
    private Button signUp;
    private Scanner in;
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
    void signUp(ActionEvent event) throws IOException {

        User user = new User(userId.getText(),password.getText(),email.getText());

        //send via json
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("TypeRE", "signUp");
        jsonRequest.addProperty("userID", user.getUserID());
        jsonRequest.addProperty("password", user.getPassword());
        jsonRequest.addProperty("emailAddress", user.getEmailAddress());

        in = new Scanner(HelloApplication.use().getInputStream());
        Request.signUpRE(HelloApplication.use(), jsonRequest);

        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String result = jsonResponse.get("response").getAsString();

        if(result.equals("signup successfully!")) {

            // Get a reference to the current stage
            Stage currentStage = (Stage) signUp.getScene().getWindow();
            // Close the current stage
            currentStage.close();
            root = FXMLLoader.load(getClass().getResource("page.fxml"));
            stage.setScene(new Scene(root));
            stage.show();

        } else{
            // if have time add label that shows
        }

    }




}

