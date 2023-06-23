package com.example.spotify.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time. Duration;
import java.util.UUID;

public class Music {

    private String trackID;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private String duration;
    private String Like;
    private String add;
    private String imageMusic;
    private String Link;


    // constructor
    public Music(String trackID,String album, String title, String artist, String genre, String duration, String Like,String add ,String imageMusic,String Link) {
        this.trackID = trackID;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
        this.Like=Like;
        this.add =add;
        this.imageMusic = imageMusic;
        this.Link=Link;
    }

    // getters and setters

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageMusic() {
        return imageMusic;
    }

    public void setImageMusic(String imageMusic) {
        this.imageMusic = imageMusic;
    }

    public String getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    public String getAdd() {
        return add;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getLike() {
        return Like;
    }

    public String getLink() {
        return Link;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public static boolean queryFound(String link) throws SQLException {
        String query = "SELECT * FROM MusicShawn WHERE linkSongs = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1,link);
        ResultSet resultSet = stmt.executeQuery();
        return resultSet.next();
    }
}