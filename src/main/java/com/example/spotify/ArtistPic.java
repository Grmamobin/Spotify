package com.example.spotify;
import com.example.spotify.DataBase.Artist;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

     void setData(Artist artist){

        Image image = new Image(getClass().getResourceAsStream(artist.getProfile()));
        imageArtist.setImage(image);

        ArtistName.setText(artist.getName());
        genre.setText(artist.getGenre());
        instagram.setText(artist.getInstagram());
        twitter.setText(artist.getTwitter());




    }

}
