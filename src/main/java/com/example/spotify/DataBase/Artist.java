package com.example.spotify.DataBase;

import java.util.List;
import java.util.UUID;

public class Artist {

    private UUID artistID;
    private String name;
    private String genre;
    private String biography;
    private String profile;
    private String twitter;
    private String instagram;
    private List<String> socialMediaLinks;
    private List<Album> albums;
    private List<Music> tracks;


    // getters and setters
    public Artist(String name){
        this.name =name;

    }
    public Artist(){}

    public UUID getArtistID() {
        return artistID;
    }

    public void setArtistID(UUID artistID) {
        this.artistID = artistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<String> getSocialMediaLinks() {
        return socialMediaLinks;
    }

    public void setSocialMediaLinks(List<String> socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Music> getTracks() {
        return tracks;
    }

    public void setTracks(List<Music> tracks) {
        this.tracks = tracks;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getProfile() {
        return profile;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    public static void queryArtist(){
        String query = "SELECT * FROM Artist WHERE name = ?";

    }
}