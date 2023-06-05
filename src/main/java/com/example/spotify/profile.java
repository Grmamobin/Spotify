package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class profile {

    @FXML
    private Button addPic;

    @FXML
    private Button backPage;

    @FXML
    private Button save;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    void addPic(ActionEvent event) {

    }

    @FXML
    void backPage(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) backPage.getScene().getWindow();

        // Close the current stage
        currentStage.close();
        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML
    void save(ActionEvent event) {

    }

}
