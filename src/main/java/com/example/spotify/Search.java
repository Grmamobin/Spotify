package com.example.spotify;

import com.example.spotify.DataBase.DatabaseConnection;
import com.example.spotify.DataBase.Music;
import com.example.spotify.Handler.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.net.HttpURLConnection;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class Search implements Initializable {

    @FXML
    private TableColumn<Music, String> Add;

    @FXML
    private TableColumn<Music, String> Album;

    @FXML
    private TableColumn<Music, String> Artists;

    @FXML
    private TableColumn<Music, String> Duration;

    @FXML
    private TableColumn<Music, String> Genre;

    @FXML
    private TableColumn<Music, String> ID;

    @FXML
    private TableColumn<Music, String> Like;

    @FXML
    private TableColumn<Music, String> SongName;

    @FXML
    private TableView<Music> tableView;

    @FXML
    private TextField keyboard;
    @FXML
    private Circle circle;
    @FXML
    private Button previousSong,nextSong,play;
    @FXML
    private ImageView IIMAGE;
    @FXML
    private Button Download,addPlayList;
    @FXML
    private Label labelSONG,labelArtist;
    private Scanner in;
    private MediaPlayer mediaPlayer2;
    private boolean orNor = false;

    ObservableList<Music> listSearch = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String viewSearch = "SELECT Id,Album,songName,Artists,Genre,Duration,Like,[Add To PlayList],Image,LinkSongs FROM Music";
        try {
            Statement statement = DatabaseConnection.connectPlz().createStatement();
            ResultSet resultSet = statement.executeQuery(viewSearch);

            while (resultSet.next()) {

                Music music1 = new Music(resultSet.getString("ID"),
                        resultSet.getString("Album"),
                        resultSet.getString("SongName"),
                        resultSet.getString("Artists"),
                        resultSet.getString("Genre"),
                        resultSet.getString("Duration"),
                        resultSet.getString("Like"),
                        resultSet.getString("Add to playList"),
                        resultSet.getString("Image"),
                        resultSet.getString("LinkSongs"));
                listSearch.add(music1);
            }
            ID.setCellValueFactory(new PropertyValueFactory<>("trackID"));
            Album.setCellValueFactory(new PropertyValueFactory<>("album"));
            SongName.setCellValueFactory(new PropertyValueFactory<>("title"));
            Artists.setCellValueFactory(new PropertyValueFactory<>("artist"));
            Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            Duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            Like.setCellValueFactory(new PropertyValueFactory<>("Like"));
            Add.setCellValueFactory(new PropertyValueFactory<>("add"));

            tableView.setItems(listSearch);

            FilteredList<Music> DATA = new FilteredList<>(listSearch, b -> true);
            keyboard.textProperty().addListener((observable, oldValue, newValue) -> {
                DATA.setPredicate(music -> {
                    if (newValue == null || newValue.isEmpty() || oldValue == null || oldValue.isBlank()) {
                        return true;
                    }
                    String search = newValue.toLowerCase();
                    return music.getTitle().toLowerCase().contains(search)
                            || music.getAlbum().toLowerCase().contains(search)
                            || music.getArtist().toLowerCase().contains(search)
                            || music.getGenre().toLowerCase().contains(search);
                });
            });

            SortedList<Music> sortedList = new SortedList<>(DATA);
            sortedList.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedList);

        } catch (SQLException e) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }

    public void selectSong() throws IOException {

        Music music = tableView.getSelectionModel().getSelectedItem();

        int num = tableView.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }

        String url = music.getImageMusic();
        String newFilePath = url.replace("/Users/macbookpro/Desktop/Spotify/src/main/resources", "");
        Image image1 = new Image(getClass().getResourceAsStream(newFilePath));
        circle.setFill(new ImagePattern(image1));
        labelArtist.setText(music.getArtist());
        labelSONG.setText(music.getTitle());

        //send via json
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("TypeRE", "listen to this music");
        jsonRequest.addProperty("title", music.getTitle());

        in = new Scanner(HelloApplication.use().getInputStream());
        Request.everyRE(HelloApplication.use(), jsonRequest);

        String response = in.nextLine();
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        String result = jsonResponse.get("link").getAsString(); //receive song via server
        String link = result.replaceAll(" ", "%20");
        url = "file://" + link;
        mediaPlayer2 = new MediaPlayer(new Media(url));

        play.setOnAction(event ->{
            mediaPlayer2.play();
            Image image2;
            if (music != null) {
                if (!orNor) {
                    InputStream Stream = getClass().getResourceAsStream("/com/example/spotify/Pictures/play-2.png");
                    image2 = new Image(Stream);
                    orNor = true;
                } else {
                    InputStream Stream = getClass().getResourceAsStream("/com/example/spotify/Pictures/pause-button.png");
                    image2 = new Image(Stream);
                    mediaPlayer2.pause();
                    orNor = false;
                }
                IIMAGE.setImage(image2);
            }
            mediaPlayer2.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mediaPlayer2.seek(javafx.util.Duration.ZERO);
                    mediaPlayer2.play();
                }
            });
        });
        previousSong.setOnAction(event -> {
            int currentIndex = tableView.getSelectionModel().getSelectedIndex();

            if (currentIndex > 0) {
                tableView.getSelectionModel().select(currentIndex - 1);

                try {
                    selectSong();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        nextSong.setOnAction(event -> {
            int currentIndex = tableView.getSelectionModel().getSelectedIndex();

            if (currentIndex >= 0) {
                tableView.getSelectionModel().select(currentIndex + 1);

                try {
                    selectSong();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @FXML
    void Download() throws IOException {

        //Download the song
        Music music = tableView.getSelectionModel().getSelectedItem();
        JsonObject jsonRE = new JsonObject();
        jsonRE.addProperty("TypeRE", "download the song");
        jsonRE.addProperty("trackID",music.getTrackID());

        in = new Scanner(HelloApplication.use().getInputStream());
        Request.everyRE(HelloApplication.use(), jsonRE);

        String RS = in.nextLine();
        JsonObject jsonRS = new Gson().fromJson(RS, JsonObject.class);
        String results = jsonRS.get("link").getAsString(); //receive song via server

        String link = results.replaceAll(" ", "%20");
        String fileURL = "file:///"+link;
        String saveDir = "/Users/macbookpro/Desktop/Spotify/src/main/resources/com/example/spotify/Downloaded/";

        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);

        try (InputStream in = new URL(fileURL).openStream()) {
            Files.copy(in,Paths.get(saveDir + fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
