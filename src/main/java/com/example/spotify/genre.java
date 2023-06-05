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
    void back(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) back.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        //stage.setMaximized(true);
        stage.setScene(new Scene(root));
        stage.show();

    }

}
