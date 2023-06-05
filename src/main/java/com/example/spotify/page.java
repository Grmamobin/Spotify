package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.example.spotify.DataBase.User;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.Objects;
import javafx.stage.StageStyle;
public class page {

    @FXML
    private Button Artists;

    @FXML
    private Label Home;

    @FXML
    private Label Library;

    @FXML
    private Label LikedSongs;

    @FXML
    private Button Taylor;

    @FXML
    private Button Tom;

    @FXML
    private Button Weeknd;

    @FXML
    private Label createPlayList;

    @FXML
    private Button july;

    @FXML
    private Label playLists;

    @FXML
    private Button profile;

    @FXML
    private Button search;
    @FXML
    private Button genre;
    @FXML
    private TextField searchfield;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
     void ProfileClick() throws IOException {

        // Get a reference to the current stage
        Stage currentStage = (Stage) profile.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Open the new stage/window
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Scene scene = new Scene(root);
        Stage profileStage = new Stage();
        profileStage.initStyle(StageStyle.DECORATED);
        profileStage.setScene(scene);
        profileStage.setTitle("Profile");
        profileStage.show();


    }
    @FXML
    void Artists(ActionEvent event) throws IOException {
        // Get a reference to the current stage
        Stage currentStage = (Stage) profile.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Open the new stage/window
        Parent root = FXMLLoader.load(getClass().getResource("Artists.fxml"));
        Scene scene = new Scene(root,1000,1250);
        Stage profileStage = new Stage();
        profileStage.initStyle(StageStyle.DECORATED);
        profileStage.setScene(scene);
        profileStage.setTitle("Profile");
        profileStage.show();


    }

    @FXML
    void genre(ActionEvent event) throws IOException {

        // Get a reference to the current stage
        Stage currentStage = (Stage) genre.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Open the new stage/window
        Parent root = FXMLLoader.load(getClass().getResource("genre.fxml"));
        Scene scene = new Scene(root);
        Stage profileStage = new Stage();
        profileStage.initStyle(StageStyle.DECORATED);
        profileStage.setScene(scene);
        profileStage.setTitle("Genre");
        profileStage.show();

    }


}
