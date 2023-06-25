package com.example.spotify;

import com.example.spotify.DataBase.Music;
import com.example.spotify.DataBase.DatabaseConnection;
import com.example.spotify.Handler.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChainSmokers implements Initializable {
    @FXML
    private ImageView image;
    @FXML
    private Circle circle;
    @FXML
    private TableColumn<Music, String> AlbumSong;

    @FXML
    private TableColumn<Music, Integer> IdSong;

    @FXML
    private TableColumn<Music, String> SongName;

    @FXML
    private TableColumn<Music, String> addSong;

    @FXML
    private TableColumn<Music, String> artistsSong;

    @FXML
    private TableView<Music> availableSongs;

    @FXML
    private TableColumn<Music, String> durationSong;

    @FXML
    private TableColumn<Music, String> genreSong;

    @FXML
    private ImageView imageSONG;

    @FXML
    private TableColumn<Music, String> likeSong;
    @FXML
    private Button Twitter;
    @FXML
    private Button Instagram;
    @FXML
    private Button before;
    @FXML
    private ImageView IIMAGE;
    @FXML
    private Button previousSong,nextSong,play;
    private Connection connection;
    private PreparedStatement prepared;
    private Statement statement;
    private ResultSet resultSet;
    private Image images;
    private MediaPlayer mediaPlayer2;
    private Scanner in;
    private boolean orNor = false;
    @FXML
    void insta(ActionEvent event) {
        String url = "https://www.instagram.com/thechainsmokers/";
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void twitter(ActionEvent event) {
        String url = "https://twitter.com/TheChainsmokers";
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public ObservableList<Music> dataList() throws SQLException {
        //connect to database to select your desire song from shawn
        ObservableList<Music> MusicList = FXCollections.observableArrayList();

        String query = "SELECT * FROM Music WHERE artists = ?";
        connection = DatabaseConnection.connectPlz();
        try {
            prepared = connection.prepareStatement(query);
            prepared.setString(1, "The ChainSmokers");
            resultSet = prepared.executeQuery();
            while (resultSet.next()) {

                Music music = new Music(resultSet.getString("ID"),
                        resultSet.getString("Album"),
                        resultSet.getString("SongName"),
                        resultSet.getString("Artists"),
                        resultSet.getString("Genre"),
                        resultSet.getString("Duration"),
                        resultSet.getString("Like"),
                        resultSet.getString("Add to playList"),
                        resultSet.getString("Image"),
                        resultSet.getString("LinkSongs"));

                MusicList.add(music);
            }
        }catch (Exception e){e.printStackTrace();}
        return MusicList;
    }

    private ObservableList<Music>listMusic;
    public void showAllMusic() throws SQLException {
        listMusic = dataList();
        IdSong.setCellValueFactory(new PropertyValueFactory<>("trackID"));
        AlbumSong.setCellValueFactory(new PropertyValueFactory<>("album"));
        SongName.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistsSong.setCellValueFactory(new PropertyValueFactory<>("artist"));
        genreSong.setCellValueFactory(new PropertyValueFactory<>("genre"));
        durationSong.setCellValueFactory(new PropertyValueFactory<>("duration"));
        likeSong.setCellValueFactory(new PropertyValueFactory<>("Like"));
        addSong.setCellValueFactory(new PropertyValueFactory<>("add"));

        availableSongs.setItems(listMusic);

    }
    public void selectSong() throws IOException {

        Music music = availableSongs.getSelectionModel().getSelectedItem();
        int num = availableSongs.getSelectionModel().getFocusedIndex();
        if ((num-1)<-1){ return;}
        String url = "file:"+music.getImageMusic();
        images = new Image(url,234,250,false,true);
        imageSONG.setImage(images);

        //send via json
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("TypeRE", "listen to this music");
        jsonRequest.addProperty("title",music.getTitle());
        System.out.println(music.getTitle());
        in = new Scanner(HelloApplication.use().getInputStream());
        Request.everyRE(HelloApplication.use(), jsonRequest);

        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String result = jsonResponse.get("link").getAsString(); //receive song via server
        String link = result.replaceAll(" ", "%20");
        url = "file://" + link;
        mediaPlayer2 = new MediaPlayer(new Media(url));

        //play and pause and previous and next
        play.setOnAction(event ->{
            mediaPlayer2.play();
            Image image1;
            if (music != null) {
                if (!orNor) {
                    InputStream Stream = getClass().getResourceAsStream("/com/example/spotify/Pictures/play-2.png");
                    image1 = new Image(Stream);
                    orNor = true;
                } else {
                    InputStream Stream = getClass().getResourceAsStream("/com/example/spotify/Pictures/pause-button.png");
                    image1 = new Image(Stream);
                    mediaPlayer2.pause();
                    orNor = false;
                }
                IIMAGE.setImage(image1);
            }
        });
        previousSong.setOnAction(event -> {
            int currentIndex = availableSongs.getSelectionModel().getSelectedIndex();

            if (currentIndex > 0) {
                availableSongs.getSelectionModel().select(currentIndex - 1);

                try {
                    selectSong();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        nextSong.setOnAction(event -> {
            int currentIndex = availableSongs.getSelectionModel().getSelectedIndex();

            if (currentIndex >= 0) {
                availableSongs.getSelectionModel().select(currentIndex + 1);

                try {
                    selectSong();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    @FXML
    void beforeMedia(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1 = new Image(getClass().getResourceAsStream("/com/example/spotify/Pictures/CHAINSMOKERS_CCAB3B69-EAF5-DD56-6B91AA40E3C4B463_ccab7e72-bf50-1754-abe29217818bd565.jpg"));
        circle.setFill(new ImagePattern(image1));

        try {
            showAllMusic();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

