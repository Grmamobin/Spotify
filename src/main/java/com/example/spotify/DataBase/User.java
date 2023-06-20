package com.example.spotify.DataBase;

import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.ResultSet;

public class User {

    private String userID;
    private String username;
    private String emailAddress;
    private String password;
    private Image profilePicture;
    private List<Playlist> playlistsCreated;
    private List<Playlist> playlistsLiked;

    // constructor
    public User(String userID, String password, String emailAddress) {
        this.userID = userID;
        this.username = getUsername();
        this.emailAddress = emailAddress;
        this.password = password;
        this.profilePicture = null;
        this.playlistsCreated = new ArrayList<>();
        this.playlistsLiked = new ArrayList<>();
    }
    public User(String userID, String password) {
        this.userID = userID;
        this.username = getUsername();
        this.emailAddress = getEmailAddress();
        this.password = password;
        this.profilePicture = getProfilePicture();
        this.playlistsCreated = getPlaylistsCreated();
        this.playlistsLiked = getPlaylistsLiked();
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

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
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

    public static void querySignUp(User user) throws SQLException {

        RandomUserIDGenerator(user);
        Connection conn = DatabaseConnection.connectPlz();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(userID, username, password, emailAddress) VALUES (?,?,?,?)");
        //DatabaseConnection database= new DatabaseConnection();

        stmt.setString(1, user.getUserID());
        stmt.setString(2, user.getUsername());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getEmailAddress());
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
    public static void queryLogin(User user) throws SQLException{

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.executeQuery();
    }

    public static boolean IsUserIdExist(String userID) throws SQLException {

        String query = "SELECT * FROM Users WHERE userID = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1,userID);
        ResultSet resultSet = stmt.executeQuery();
        return resultSet.next();

    }
    public static boolean IsPassExist(String password) throws SQLException {

        String query = "SELECT * FROM Users WHERE password = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1,password);
        ResultSet resultSet = stmt.executeQuery();
        return resultSet.next();

    }

    public static void RandomUserIDGenerator(User user) {

        UUID uuid = UUID.randomUUID();
        String userName = "@" + user.getUserID() + uuid.toString().replace("-", "").substring(0, 8);
        user.setUsername(userName);

    }

}