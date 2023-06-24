package com.example.spotify;

import com.example.spotify.DataBase.DatabaseConnection;
import com.example.spotify.DataBase.Music;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    ObservableList<Music> listSearch = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String viewSearch = "SELECT Id,Album,songName,Artists,Genre,Duration,Like,[Add To PlayList] FROM Music";
        try {
            Statement statement = DatabaseConnection.connectPlz().createStatement();
            ResultSet resultSet = statement.executeQuery(viewSearch);

            while (resultSet.next()) {

                String ID = resultSet.getString("id");
                String Album = resultSet.getString("Album");
                String SongName = resultSet.getString("songName");
                String Artists = resultSet.getString("Artists");
                String Genre = resultSet.getString("Genre");
                String Duration = resultSet.getString("Duration");
                String Like = resultSet.getString("Like");
                String ADD = resultSet.getString("add to playList");

                listSearch.add(new Music(ID, Album, SongName, Artists, Genre, Duration, Like, ADD));

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
}
