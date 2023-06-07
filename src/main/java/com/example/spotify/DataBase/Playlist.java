package com.example.spotify.DataBase;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Playlist {

    private UUID playlistID;
    private String title;
    private String description;
    private User creator;
    private int numLikes;
    private List<Music> tracks;

    // constructor
    public Playlist(UUID playlistID, String title, String description, User creator, int numLikes) {
        this.playlistID = playlistID;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.numLikes = numLikes;
        this.tracks = new ArrayList<>();
    }

    // getters and setters

    public UUID getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(UUID playlistID) {
        this.playlistID = playlistID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public List<Music> getTracks() {
        return tracks;
    }

    public void setTracks(List<Music> tracks) {
        this.tracks = tracks;
    }
}