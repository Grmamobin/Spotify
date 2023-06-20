package com.example.spotify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HelloApplication extends Application {

    private static Socket clientSocket;
    private Scanner in;

    @Override
    public void start(Stage stage) throws IOException {

        clientSocket = new Socket("localhost", 8888);
        in = new Scanner(clientSocket.getInputStream());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu_input.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 500);
        stage.setTitle("SPOTIFY");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
    public static Socket use(){
        return clientSocket;
    }

}