package com.example.spotify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HelloApplication extends Application {
    private Socket serverSocket;
    private Scanner in;
    public HelloApplication(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }
    @Override
    public void start(Stage stage) {
        try {
            try {
                in = new Scanner(serverSocket.getInputStream());
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu_input.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 550, 500);
                stage.setTitle("SPOTIFY");
                stage.setScene(scene);
                stage.show();
            } finally {
                in.close();
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        } finally {
            System.out.println("client " + serverSocket.getRemoteSocketAddress() + "has been disconnected");
        }

    }

    public static void main(String[] args) {
        launch();
    }
}