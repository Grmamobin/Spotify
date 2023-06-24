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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.example.spotify.DataBase.User;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.media.MediaPlayer.Status;

import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    private Button playlists;

    @FXML
    private Button profile;

    @FXML
    private Button search;
    @FXML
    private Button genre;
    @FXML
    private Button create ,Search;
    @FXML
    private TextField searchfield;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();
    private Scanner in;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    Status status = Status.STOPPED;
    private int clickCount = 0;
    private int ClickCount = 0;
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

    @FXML
    void createPlayList(ActionEvent event) throws IOException {

        // Open the new stage/window
        Parent root = FXMLLoader.load(getClass().getResource("createPlayList.fxml"));
        Scene scene = new Scene(root);
        Stage profileStage = new Stage();
        profileStage.initStyle(StageStyle.DECORATED);
        profileStage.setScene(scene);
        profileStage.setTitle("create");
        profileStage.show();

    }
    @FXML
    void playLists(ActionEvent event) throws IOException {

        // Open the new stage/window
        Parent root = FXMLLoader.load(getClass().getResource("playList.fxml"));
        Scene scene = new Scene(root);
        Stage profileStage = new Stage();
        profileStage.initStyle(StageStyle.DECORATED);
        profileStage.setScene(scene);
        profileStage.setTitle("playLists");
        profileStage.show();
    }
    @FXML
    void Search(ActionEvent event) throws IOException {
        // Open the new stage/window
        Parent root = FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene scene = new Scene(root);
        Stage profileStage = new Stage();
        profileStage.initStyle(StageStyle.DECORATED);
        profileStage.setScene(scene);
        profileStage.setTitle("Search");
        profileStage.show();
    }

    @FXML
    void neighborhoodSong(ActionEvent event) throws IOException {

        //send via json
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("TypeRE", "listen to this music");
        jsonRequest.addProperty("title", "SoftCore");

        in = new Scanner(HelloApplication.use().getInputStream());
        Request.everyRE(HelloApplication.use(), jsonRequest);

        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String result = jsonResponse.get("link").getAsString(); //receive song via server

        //play the song and stop it after the second press
        if (mediaPlayer2 == null) {
            String link = result.replaceAll(" ", "%20");
            String url = "file://" + link;
            mediaPlayer2 = new MediaPlayer(new Media(url));
            System.out.println(url);
        }
        ClickCount++;
        if (ClickCount % 2 == 1) {
            mediaPlayer2.play();

        } else {
            mediaPlayer2.pause();
        }

    }


    @FXML
    void taylorSong(ActionEvent event) throws IOException {
        //send via json
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("TypeRE", "listen to this music");
        jsonRequest.addProperty("title", "Gold Rush");

        in = new Scanner(HelloApplication.use().getInputStream());
        Request.everyRE(HelloApplication.use(), jsonRequest);

        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String result = jsonResponse.get("link").getAsString(); //receive song via server

        //play the song and stop it after the second press
        if (mediaPlayer == null) {
            String link = result.replaceAll(" ", "%20");
            String url = "file://" + link;
            mediaPlayer = new MediaPlayer(new Media(url));
            System.out.println(url);
        }
        clickCount++;
        if (clickCount % 2 == 1) {
            mediaPlayer.play();

            if(mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration())) {
                mediaPlayer.seek(Duration.seconds(0));
            }

        } else {
            mediaPlayer.pause();
        }
    }

}
