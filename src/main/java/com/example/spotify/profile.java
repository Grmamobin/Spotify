package com.example.spotify;

import com.example.spotify.DataBase.DatabaseConnection;
import com.example.spotify.DataBase.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.example.spotify.Handler.Request;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class profile implements Initializable {

    @FXML
    private Button addPic,Save;
    @FXML
    private Circle Circle;

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
    void addPic(ActionEvent event) throws IOException, SQLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add Picture");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            String newFilePath = selectedFile.getPath();

            JsonObject jsonRequest = new JsonObject();
            jsonRequest.addProperty("TypeRE", "edit picture");
            jsonRequest.addProperty("link File",newFilePath);

            BufferedReader in = new BufferedReader(new InputStreamReader(HelloApplication.use().getInputStream()));
            Request.everyRE(HelloApplication.use(), jsonRequest);

            String response = in.readLine();
            JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
            String result = jsonResponse.get("link").getAsString();
            String FilePath = result.replace("/Users/macbookpro/Desktop/Spotify/src/main/resources", "");
            Image image1 = new Image(getClass().getResourceAsStream(FilePath));
            Circle.setFill(new ImagePattern(image1));
        }


    }
    @FXML
    void Save(ActionEvent event) throws IOException {


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

        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("TypeRE", "show profile");
        jsonRequest.addProperty("user",User.getUserID());

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(HelloApplication.use().getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Request.everyRE(HelloApplication.use(), jsonRequest);

        String response = null;
        try {
            response = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String result = jsonResponse.get("response").getAsString();

        if(result.equals("you have profile")){
            String url = jsonResponse.get("link").getAsString();
            String newFilePath = url.replace("/Users/macbookpro/Desktop/Spotify/src/main/resources", "");
            Image image1 = new Image(getClass().getResourceAsStream(newFilePath));
            Circle.setFill(new ImagePattern(image1));
        }else{return;}


    }
}
