package com.example.spotify.DataBase;

import java.time.LocalDate;
import java.time. Duration;
import java.util.UUID;

public class Music {

    private UUID trackID;
    private String title;
    private Artist artist;
    private Album album;
    private Genre genre;
    private Duration duration;
    private LocalDate releaseDate;
    private int popularity;

    // constructor
    public Music(UUID trackID, String title, Artist artist, Album album, Genre genre, Duration duration, LocalDate releaseDate, int popularity) {
        this.trackID = trackID;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    // getters and setters

    public UUID getTrackID() {
        return trackID;
    }

    public void setTrackID(UUID trackID) {
        this.trackID = trackID;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
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
}