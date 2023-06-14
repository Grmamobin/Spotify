package com.example.spotify.DataBase;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class User {

    private String userID;
    private String username;
    private String emailAddress;
    private String password;
    private String profilePicture;
    private List<Playlist> playlistsCreated;
    private List<Playlist> playlistsLiked;

    // constructor
    public User(String username, String emailAddress, String password) {
        this.userID = userID;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.profilePicture = profilePicture;
        this.playlistsCreated = new ArrayList<>();
        this.playlistsLiked = new ArrayList<>();
    }


    // getters and setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Playlist> getPlaylistsCreated() {
        return playlistsCreated;
    }

    public void setPlaylistsCreated(List<Playlist> playlistsCreated) {
        this.playlistsCreated = playlistsCreated;
    }

    public List<Playlist> getPlaylistsLiked() {
        return playlistsLiked;
    }

    public void setPlaylistsLiked(List<Playlist> playlistsLiked) {
        this.playlistsLiked = playlistsLiked;
    }

}