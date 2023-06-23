package com.example.spotify;

import com.example.spotify.DataBase.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Scanner;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.example.spotify.Handler.Request;
import com.google.gson.Gson;

public class profile implements Initializable {

    @FXML
    private Button addPic;

    @FXML
    private Button backPage;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label password;

    @FXML
    private Label ID;
    private Scanner in;

    @FXML
    void addPic(ActionEvent event) throws IOException {

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("TypeRE", "edit picture");

        in = new Scanner(HelloApplication.use().getInputStream());
        Request.everyRE(HelloApplication.use(), jsonRequest);

        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String result = jsonResponse.get("response").getAsString();

        if (result.equals("change photo successfully!")){
            //TODO show photo must be there after response successfully
        } else{

        }

    }

    @FXML
    void backPage(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) backPage.getScene().getWindow();

        // Close the current stage
        currentStage.close();
        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        stage.setScene(new Scene(root));
        stage.show();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ID.setText(User.getUserID());
        password.setText(User.getPassword());

        if(User.getUsername() == (null)) {
            try {
                name.setText(User.queryFindUsername(User.getUserID()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }if(User.getEmailAddress() == (null)){
            try {
                email.setText(User.queryFindEmailAddress(User.getUserID()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            name.setText(User.getUsername());
            email.setText(User.getEmailAddress());
        }
    }
}
