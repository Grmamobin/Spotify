package com.example.spotify;

import com.example.spotify.Handler.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.example.spotify.DataBase.User;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class login {

    @FXML
    private ImageView hidden;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userId;
    @FXML
    private Label error;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();
    private Scanner in;


    @FXML
    void login(ActionEvent event)  throws IOException {

        User user = new User(userId.getText(),password.getText());

        //send via json
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("TypeRE", "login");
        jsonRequest.addProperty("userID", userId.getText());
        jsonRequest.addProperty("password", password.getText());

        in = new Scanner(HelloApplication.use().getInputStream());
        Request.everyRE(HelloApplication.use(), jsonRequest);

        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String result = jsonResponse.get("response").getAsString();

        if(result.equals("login successfully!")) {
            // Get a reference to the current stage
            Stage currentStage = (Stage) login.getScene().getWindow();
            // Close the current stage
            currentStage.close();
            root = FXMLLoader.load(getClass().getResource("page.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            error.setText("userID is wrong or your password is not correct plz try again");
        }

    }


}
