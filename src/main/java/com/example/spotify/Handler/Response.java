package com.example.spotify.Handler;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.OutputStream;
import org.json.JSONObject;
import java.io.IOException;
public class Response {
    public static void signupRS(Socket serverSocket, Boolean flag){
        PrintWriter out = null;
        try {
            //Connecting to output stream
            OutputStream outputStream = serverSocket.getOutputStream();
            out = new PrintWriter(outputStream);

            //Json
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("Type", "signup");
            if (!flag){
                jsonResponse.put("response", "signup is done successfully");
            } else {
                jsonResponse.put("response", "this userId is already exist");
            }

            //Sending json object over socket
            System.out.println("SENDING: " + jsonResponse);
            out.println(jsonResponse);
            out.flush();
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
}