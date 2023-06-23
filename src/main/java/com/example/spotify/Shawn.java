package com.example.spotify;

import com.example.spotify.DataBase.Music;
import com.example.spotify.DataBase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Shawn implements Initializable {
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
    private Button pauseSongss;

    @FXML
    private Button playSongss;
    @FXML
    private Button Twitter;
    @FXML
    private Button Instagram;
    @FXML
    private Button before;
    @FXML
    private Button next,unlike;
    @FXML
    private Button back,after;
    private Connection connection;
    private PreparedStatement prepared;
    private Statement statement;
    private ResultSet resultSet;
    private Image images;
    private  MediaPlayer mediaPlayer;
    private Music music;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    void insta(ActionEvent event) {
        String url = "https://www.instagram.com/shawnmendes/";
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void twitter(ActionEvent event) {
        String url = "https://twitter.com/ShawnMendes";
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
          prepared.setString(1, "Shawn Mendes");
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
    public void selectSong(){
        Music music = availableSongs.getSelectionModel().getSelectedItem();
        int num = availableSongs.getSelectionModel().getFocusedIndex();
        if ((num-1)<-1){ return;}
        String url = "file:"+music.getImageMusic();
        images = new Image(url,216,237,false,true);
        imageSONG.setImage(images);

    }

    public void SongPlayer(){
        final Lock playerLock = new ReentrantLock();
        music = availableSongs.getSelectionModel().getSelectedItem();
        int num = availableSongs.getSelectionModel().getFocusedIndex();
        if ((num-1)<-1){ return;}
        String link = music.getLink().replaceAll(" ", "%20");
        String url = "file://"+link;
        //System.out.println(url);
        playerLock.lock(); // Acquire lock before accessing shared resource

        try {
            mediaPlayer = new MediaPlayer(new Media(url));
            mediaPlayer.play();
        } finally {
            playerLock.unlock(); // Release lock when done accessing shared resource
        }

    }
    @FXML
    void pause(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        } else {}
    }

    @FXML
    void play(ActionEvent event) {
       //mediaPlayer.play();
    }
    @FXML
    void nextMedia(ActionEvent event) {
    //SELECT * FROM songs WHERE song_index = (current_index + 1)  todo

    }
    @FXML
    void beforeMedia(ActionEvent event) {
        if(mediaPlayer.getCurrentTime().toSeconds() <= 5){
            mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(0)));
        } else {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(5)));
        }
    }
    @FXML
    void back() throws IOException {
        Stage currentStage = (Stage) back.getScene().getWindow();
        // Close the current stage
        currentStage.close();
        root = FXMLLoader.load(getClass().getResource("Artists.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void after() throws IOException {
        Stage currentStage = (Stage) after.getScene().getWindow();
        // Close the current stage
        currentStage.close();
        root = FXMLLoader.load(getClass().getResource("ChainSmokers.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1 = new Image(getClass().getResourceAsStream("/com/example/spotify/Pictures/circle.jpg"));
        circle.setFill(new ImagePattern(image1));

        try {
            showAllMusic();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void unlike() {
        unlike.setText("Like");

/*        // set the text for the like button when it's selected
        unlike.setSelected(false);
        unlike.setDisable(false);
        unlike.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unlike.setText("Unlike");
            } else {
                unlike.setText("Like");
            }
        });*/
    }
}

