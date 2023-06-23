package com.example.spotify;

import com.example.spotify.DataBase.DatabaseConnection;
import com.example.spotify.DataBase.Music;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.fxml.FXML;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Hip_Hop implements Initializable {
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
    private TableColumn<Music, String> likeSong;
    @FXML
    private ImageView imageSONG;

    @FXML
    private Button pause;

    @FXML
    private Button play;

    private Connection connection;
    private PreparedStatement prepared;
    private Statement statement;
    private ResultSet resultSet;
    private Image images;
    public ObservableList<Music> dataList() throws SQLException {
        //connect to database to select your desire song from shawn
        ObservableList<Music> MusicList = FXCollections.observableArrayList();

        String query = "SELECT * FROM Music WHERE Genre = ?";
        connection = DatabaseConnection.connectPlz();
        try {
            prepared = connection.prepareStatement(query);
            prepared.setString(1, "Hip_Hop");
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
        images = new Image(url,234,250,false,true);
        imageSONG.setImage(images);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showAllMusic();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

/*    public void SongPlayer(String filepath, ImageView playPauseIcon){
        Media media = new Media(filepath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        this.playPauseIcon = playPauseIcon;
        playIconImage = new Image("path/to/play/icon.png");
        pauseIconImage = new Image("path/to/pause/icon.png");

        // Add listener to toggle play/pause icon when player status changes
        mediaPlayer.setOnPlaying(() -> setPlayPauseIcon(pauseIconImage));
        mediaPlayer.setOnPaused(() -> setPlayPauseIcon(playIconImage));
    }*/

}
