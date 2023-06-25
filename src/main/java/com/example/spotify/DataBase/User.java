package com.example.spotify.DataBase;

import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class User {

    private static String userID;
    private static String username;
    private static String emailAddress;
    private static String password;
    private Image profilePicture;
    private String playlists;
    private int Liked;

    // constructor
    public User(String userID, String password, String emailAddress) {
        this.userID = userID;
        this.username = getUsername();
        this.emailAddress = emailAddress;
        this.password = password;
        this.profilePicture = null;
        this.playlists = playlists;
        this.Liked  = Liked;
    }
    public User(String userID, String password) {
        this.userID = userID;
        this.username = getUsername();
        this.emailAddress = getEmailAddress();
        this.password = password;
        this.profilePicture = getProfilePicture();
        this.playlists = getPlaylists();
        this.Liked  = getLiked();
    }
    public User(String userID,String username,String password,String emailAddress) {
        this.userID = userID;
        this.username = getUsername();
        this.emailAddress = getEmailAddress();
        this.password = password;
        this.profilePicture = getProfilePicture();
        this.playlists = getPlaylists();
        this.Liked  = getLiked();
    }


    // getters and setters

    public static String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public static String getPassword() {
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

    public int getLiked() {
        return Liked;
    }

    public String getPlaylists() {
        return playlists;
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

    /*    //make like list and playlist for user
        PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO likeUsers(userID, username, password, emailAddress) VALUES (?,?,?,?)");

        stmt2.setString(1, user.getUserID());
        stmt2.setInt(2, user.getLiked());
        try {
            stmt2.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }

        PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO playListUsers(userID, username, password, emailAddress) VALUES (?,?,?,?)");
        stmt3.setString(1, user.getUserID());
        stmt3.setString(2, user.getPlaylists());
        try {
            stmt3.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }*/

    }

    public static boolean IsUserIdExist(String userID) throws SQLException {

        String query = "SELECT * FROM Users WHERE userID = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1,userID);
        ResultSet resultSet = stmt.executeQuery();
        return resultSet.next();

    }
    public static User authorLogin(String userID, String password) throws SQLException {

        String query = "SELECT userID , username, password , emailAddress FROM Users WHERE userID = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1, userID);
        ResultSet resultSet = stmt.executeQuery();
        String unknownPass = resultSet.getString("password");

        if(password.equals(unknownPass)){
            User user = new User(userID,password,resultSet.getString("username"), resultSet.getString("emailAddress"));
            return user;
        }
        return null;
    }
    public static String queryFindUsername(String userID) throws SQLException {

        String query = "SELECT username FROM Users WHERE userID = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1,userID);
        ResultSet resultSet = stmt.executeQuery();
        return resultSet.getString("username");
    }
    public static String queryFindEmailAddress(String userID) throws SQLException {

        String query = "SELECT emailAddress FROM Users WHERE userID = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1,userID);
        ResultSet resultSet = stmt.executeQuery();
        return resultSet.getString("emailAddress");
    }
    public static boolean LikeByThisUser(String title,String userId) throws SQLException {

        String query = "SELECT title FROM likeUsers WHERE userID = ?";
        PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
        stmt.setString(1, userId);
        boolean resultSet = stmt.executeQuery().next();

        if(resultSet){ //delete form like table
        /*    PreparedStatement stmts = DatabaseConnection.connectPlz().prepareStatement("DELETE FROM likeUsers WHERE userID = ? AND title = ?");
            stmts.setString(1, userId);
            stmts.setString(2, title);
            stmts.close();
            //////////////////////////////////////////NOW FOR MUSIC COUNT DECREASE
            PreparedStatement stmts2 = DatabaseConnection.connectPlz().prepareStatement("UPDATE Music SET Like = Like - 1 WHERE SongName = ?");
            stmts2.setString(1, title);
            stmts2.executeUpdate();
            stmts2.close();*/

            return false;
        }
        //Add to like table
     /*   PreparedStatement stmts = DatabaseConnection.connectPlz().prepareStatement("INSERT INTO likeUsers(userID,title) VALUES (?,?)");
        stmts.setString(1, userId);
        stmts.setString(2, title);
        stmts.close();
        //////////////////////////////////////////NOW FOR MUSIC COUNT INCREASE
        PreparedStatement stmts2 = DatabaseConnection.connectPlz().prepareStatement("UPDATE Music SET Like = Like + 1 WHERE SongName = ?");
        stmts2.setString(1, title);
        stmts2.executeUpdate();
        stmts2.close();*/

        return true;
    }
    public static void AddLike(String userId, String title) throws SQLException {
        Connection conn = null;
        PreparedStatement stmts = null;

        try {
            conn = DatabaseConnection.connectPlz();
            stmts = conn.prepareStatement("INSERT INTO likeUsers (userID, title) VALUES (?, ?)");
            stmts.setString(1, userId);
            stmts.setString(2, title);
            int affectedRows = stmts.executeUpdate();

            System.out.println("Number of rows affected: " + affectedRows);
        } finally {
            if (stmts != null) {
                stmts.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void DeleteLike(String userId, String title) throws SQLException {
        Connection conn = null;
        PreparedStatement stmts = null;

        try {
            conn = DatabaseConnection.connectPlz();
            stmts = conn.prepareStatement("DELETE FROM likeUsers WHERE userID = ? AND title = ?");
            stmts.setString(1, userId);
            stmts.setString(2, title);
            int affectedRows = stmts.executeUpdate();

            System.out.println("Number of rows affected: " + affectedRows);
        } finally {
            if (stmts != null) {
                stmts.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public static void addProfile(String name) throws SQLException {
        String query = "UPDATE users SET profile ='"+name+"'WHERE userID ='"+User.getUserID()+"'";
        try {
            Statement stmt = DatabaseConnection.connectPlz().createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlException){
            sqlException.getStackTrace();
        }
    }
    public static String ShowProf(String userID) throws SQLException {

        String query = "SELECT profile FROM Users WHERE userID = ?";
        try{
            PreparedStatement stmt = DatabaseConnection.connectPlz().prepareStatement(query);
            stmt.setString(1, userID);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()){
                return resultSet.getString("profile");
            }
        } catch (SQLException sqlException){
            sqlException.getStackTrace();
        }
        return null;
    }



    public static void RandomUserIDGenerator(User user) {

        UUID uuid = UUID.randomUUID();
        String userName ="@"+user.getUserID() + uuid.toString().replace("-", "").substring(0, 8);
        user.setUsername(userName);

    }

}