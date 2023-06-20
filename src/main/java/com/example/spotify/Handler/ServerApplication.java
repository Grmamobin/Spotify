package com.example.spotify.Handler;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.example.spotify.DataBase.User;

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

        while (in.hasNextLine()) {
            String jsonString = in.nextLine();
            JsonObject jsonRequest = new Gson().fromJson(jsonString, JsonObject.class);
            String TypeRE = jsonRequest.get("TypeRE").getAsString();
            System.out.println("TypeRE: " + TypeRE);

            executeRE(jsonRequest); //TODO: add exit option
        }
    }

    public void executeRE(JsonObject jsonRE) throws SQLException {

        String requestType = jsonRE.get("TypeRE").getAsString();

        //request type
        if (requestType.equals("signUp")) {  // FIRST

            if (!User.IsUserIdExist(jsonRE.get("userID").getAsString())) {
                User user = new User(jsonRE.get("userID").getAsString(), jsonRE.get("password").getAsString(),jsonRE.get("emailAddress").getAsString());
                User.querySignUp(user);

                Response.signupRS(serverSocket, false);
            }
            else {
                Response.signupRS(serverSocket, true);
            }

        }

        if(requestType.equals("login")) {  //SECOND

            if(User.IsUserIdExist(jsonRE.get("userID").getAsString()) && User.IsPassExist("password")) {
                User user = new User(jsonRE.get("userID").getAsString(), jsonRE.get("password").getAsString(),jsonRE.get("emailAddress").getAsString());
                User.queryLogin(user);
                Response.loginRS(serverSocket, false);
            }
            else{
                Response.loginRS(serverSocket, true);
            }
        }

    }


}
