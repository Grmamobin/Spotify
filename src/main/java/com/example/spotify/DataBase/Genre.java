package com.example.spotify.DataBase;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Genre {

    private UUID genreID;
    private String name;
    private String description;
    private List<Genre> relatedGenres;

    // constructor
    private Genre(UUID genreID, String name, String description) {
        this.genreID = genreID;
        this.name = name;
        this.description = description;
        this.relatedGenres = new ArrayList<>();
    }

    // getters and setters

    public UUID getGenreID() {
        return genreID;
    }

    public void setGenreID(UUID genreID) {
        this.genreID = genreID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genre> getRelatedGenres() {
        return relatedGenres;
    }

    public void setRelatedGenres(List<Genre> relatedGenres) {
        this.relatedGenres = relatedGenres;
    }
}
