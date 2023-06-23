package com.example.spotify.DataBase;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Album {

    private UUID albumID;
    private String title;
    private Artist artist;
    private Genre genre;
    private LocalDate releaseDate;
    private int popularity;
    private List<Music> tracks;

    // constructor
    public Album(String title) {
        this.albumID = albumID;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.tracks = new ArrayList<>();
    }
    // getters and setters

    public UUID getAlbumID() {
        return albumID;
    }

    public void setAlbumID(UUID albumID) {
        this.albumID = albumID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public List<Music> getTracks() {
        return tracks;
    }

    public void setTracks(List<Music> tracks) {
        this.tracks = tracks;
    }
}