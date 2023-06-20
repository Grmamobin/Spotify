package com.example.spotify.Handler;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.OutputStream;
import org.json.JSONObject;
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
    public static void loginRS(Socket serverSocket , boolean problem){
        PrintWriter out = null;
        try {
            //Output stream for connecting
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            //Json Response
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("TypeRS", "login");

            if (!problem){
                jsonResponse.put("response", "login successfully!");
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

}