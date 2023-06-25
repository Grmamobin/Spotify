package com.example.spotify.Handler;
import java.io.InputStream;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.OutputStream;

import javafx.scene.image.Image;
import org.json.JSONObject;
import com.google.gson.JsonObject;

import java.io.IOException;
public class Response {
    //RS in code is related to response option
    public static void signupRS(Socket serverSocket , boolean problem){
        PrintWriter out = null;
        try {
            //Output stream for connecting
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            //Json Response
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("TypeRS", "signUp");

            if (!problem){
                jsonResponse.put("response", "signup successfully!");
            } else {
                jsonResponse.put("response", "userID already exist");
            }

            //Sending json with socket
            System.out.println("Sending: " + jsonResponse);
            out.println(jsonResponse);
            out.flush();
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
    public static void loginRS(Socket serverSocket , boolean correct, JSONObject jsonObject){
        PrintWriter out = null;
        try {
            //Output stream for connecting
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            //Json Response
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("TypeRS", "login");

            if (correct){ //if you hadn't account
                jsonResponse.put("response", "login successfully!");
                jsonResponse.put("user", jsonObject);
            } else {
                jsonResponse.put("response", "userID is wrong");
            }

            //Sending json with socket
            System.out.println("Sending: " + jsonResponse);
            out.println(jsonResponse);
            out.flush();
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
    public static void editRS(Socket serverSocket, boolean problem, String name) {
        PrintWriter out = null;
        try {
            //Connecting to output stream
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            //Json
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("response", "edit successfully");
            jsonResponse.addProperty("link", name);
            //Sending json object over the socket
            System.out.println("Sending: " + jsonResponse);
            out.println(jsonResponse);
            out.flush();
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
    public static void loadRS(Socket serverSocket, JSONObject jsonResponse,boolean correct) {
        PrintWriter out = null;
        try {
            //Connecting to output stream
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            if (correct){ //if you had
                jsonResponse.put("response", "you have profile");

            } else {
                jsonResponse.put("response", "you don't have profile");
            }

            //Sending json object over the socket
            System.out.println("Sending: " + jsonResponse);
            out.println(jsonResponse);
            out.flush();
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
    public static void listenSongRS(Socket serverSocket,boolean problem, JsonObject jsonResponse){
        PrintWriter out = null;
        try{
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            jsonResponse.addProperty("response", "you can listen to this music");

            System.out.println("Sending: " + jsonResponse);
            out.println(jsonResponse);
            out.flush();
        }
        catch (IOException io){
            io.printStackTrace();
        }

    }
    public static void likeRS(Socket serverSocket, boolean like) {
        PrintWriter out = null;
        try {
            //Output stream for connecting
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            //Json Response
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("TypeRS", "Like or unlike");

            if (like) { //if you didn't like it before
                jsonResponse.put("response", "like successfully!");
            } else { //if you liked before
                jsonResponse.put("response", "unlike successfully!");

            }

            System.out.println("Sending: " + jsonResponse);
            out.println(jsonResponse);
            out.flush();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    public static void downloadRS(Socket serverSocket,JsonObject jsonResponse){
        PrintWriter out = null;
        try{
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            jsonResponse.addProperty("response", "Download the file successfully");

            System.out.println("Sending: " + jsonResponse);
            out.println(jsonResponse);
            out.flush();
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }


}