package com.example.spotify.Handler;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.IOException;

import com.example.spotify.DataBase.Music;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.example.spotify.DataBase.User;
import javafx.scene.image.Image;
import org.json.JSONObject;
public class ServerApplication implements Runnable {
    private Socket serverSocket;
    private Scanner in;
    public ServerApplication(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }
    @Override
    public void run() {
        try {
            try {
                in = new Scanner(serverSocket.getInputStream());
                SpotifyRS();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                in.close();
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        } finally {
            System.out.println("client " + serverSocket.getRemoteSocketAddress() + " has been disconnected");

        }

    }
    public void SpotifyRS() throws SQLException {

        if (in.hasNextLine()) {
            while(in.hasNextLine()) {
                String jsonString = in.nextLine();
                JsonObject jsonRequest = new Gson().fromJson(jsonString, JsonObject.class);
                String TypeRE = jsonRequest.get("TypeRE").getAsString();
                System.out.println("TypeRE: " + TypeRE);

                executeRE(jsonRequest); //TODO: add exit option
            }
        } else{
            String jsonString = in.nextLine();
            JsonObject jsonRequest = new Gson().fromJson(jsonString, JsonObject.class);
            String TypeRE = jsonRequest.get("TypeRE").getAsString();
            System.out.println("TypeRE: " + TypeRE);

            executeRE(jsonRequest); //TODO: add exit option
        }

    }

    public void executeRE(JsonObject jsonRE) throws SQLException {

        String requestType = jsonRE.get("TypeRE").getAsString();
        System.out.println(jsonRE);

        //request type
        if (requestType.equals("signUp")) {  // FIRST

            if (!User.IsUserIdExist(jsonRE.get("userID").getAsString())) {
                User user = new User(jsonRE.get("userID").getAsString(), jsonRE.get("password").getAsString(), jsonRE.get("emailAddress").getAsString());
                User.querySignUp(user);

                Response.signupRS(serverSocket, false);
            } else {
                Response.signupRS(serverSocket, true);
            }

        }

        if (requestType.equals("login")) {  //SECOND

            String userID = jsonRE.get("userID").getAsString();
            String password = jsonRE.get("password").getAsString();

            User user = User.authorLogin(userID, password);

            if (user != null) {
                JSONObject jsonUser = new JSONObject();
                jsonUser.put("userID", user.getUserID());
                jsonUser.put("username", user.getUsername());
                jsonUser.put("password", user.getPassword());
                //Sending response
                Response.loginRS(serverSocket, true, jsonUser);
            } else {
                Response.loginRS(serverSocket, false, null);
            }
        }

        if (requestType.equals("edit picture")) { //THIRD

            String name = jsonRE.get("link File").getAsString();
            User.addProfile(name);
            Response.editRS(serverSocket, true, name);

        }
        if (requestType.equals("listen to this music")) { //FOURTH

            String name = jsonRE.get("title").getAsString();
            JsonObject file_path = Music.LinkPath(name);  //search via database to find this file exist or not
            if (file_path != null) {
                Response.listenSongRS(serverSocket, true, file_path);
            } else {
                Response.listenSongRS(serverSocket, false, file_path);
            }

        }
        if (requestType.equals("Like or unlike")) { //FIFTH

            String name = jsonRE.get("title").getAsString();
            boolean like = User.LikeByThisUser(name,User.getUserID());


            if (like) {
                //Sending response
                Response.likeRS(serverSocket, true);
                User.AddLike(name,User.getUserID());
                Music.IncreaseLike(name);

            } else {
                Response.likeRS(serverSocket, false);
                User.DeleteLike(name,User.getUserID());
                Music.DecreaseLike(name);
            }

        }
        if(requestType.equals("download the song")){ //SIXTH

            String name = jsonRE.get("trackID").getAsString();
            JsonObject file = Music.LinkByID(name);  //search via database to find this file exist or not
            if (file!= null) {
                Response.downloadRS(serverSocket, file);
            } else {
                Response.downloadRS(serverSocket, file);
            }

        }
        if (requestType.equals("show profile")) { //SEVENTH

            String userID = jsonRE.get("user").getAsString();

            String link = User.ShowProf(userID);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("link",link);

            if (jsonResponse != null){
                //Sending response
                Response.loadRS(serverSocket,jsonResponse,true);
            }
            else {
                Response.loadRS(serverSocket,jsonResponse,false);
            }

        }


    }


}
