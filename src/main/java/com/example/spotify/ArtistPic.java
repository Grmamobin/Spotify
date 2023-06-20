package com.example.spotify;
import com.example.spotify.DataBase.Artist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class ArtistPic {

    @FXML
    private Label ArtistName;

    @FXML
    private Label genre;

    @FXML
    private ImageView imageArtist;

    @FXML
    private Label instagram;

    @FXML
    private Label twitter;
    @FXML
    private Button ChosenSinger;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();
    private Artist artist;
    private MyListener myListener;
    @FXML
    public void click(ActionEvent actionEvent) throws IOException {
        myListener.onClickListener(artist);
    }

     void setData(Artist artist,MyListener myListener){
        this.artist = artist;
        this.myListener = myListener;
        Image image = new Image(getClass().getResourceAsStream(artist.getProfile()));
        imageArtist.setImage(image);

        ArtistName.setText(artist.getName());
        genre.setText(artist.getGenre());
        instagram.setText(artist.getInstagram());
        twitter.setText(artist.getTwitter());

    }

}
