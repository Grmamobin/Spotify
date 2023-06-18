package com.example.spotify.Handler;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import com.google.gson.JsonObject;
import org.json.JSONObject;

public class Request {
    public static void signupRE(Socket clientSocket, JsonObject jsonRequest) {
        PrintWriter out = null;
        try {
            //Sending request to server
            out = new PrintWriter(clientSocket.getOutputStream());
            out.println(jsonRequest);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loginRE(Socket clientSocket, JsonObject jsonRequest) {
        PrintWriter out = null;
        try {
            //Sending request to server
            out = new PrintWriter(clientSocket.getOutputStream());
            out.println(jsonRequest);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}