package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class genre {
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    private Button back;
    @FXML
    private Button HipHop;
    @FXML
    private Button Indie;

    @FXML
    private Button POP;


    @FXML
    void back(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) back.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        //stage.setMaximized(true);
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    void HipHop() throws IOException {

        root = FXMLLoader.load(getClass().getResource("Hip_Hop.fxml"));
        //stage.setMaximized(true);
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void POP() throws IOException {

        root = FXMLLoader.load(getClass().getResource("POP.fxml"));
        //stage.setMaximized(true);
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void Indie() throws IOException {

        root = FXMLLoader.load(getClass().getResource("Indie.fxml"));
        //stage.setMaximized(true);
        stage.setScene(new Scene(root));
        stage.show();
    }


}
