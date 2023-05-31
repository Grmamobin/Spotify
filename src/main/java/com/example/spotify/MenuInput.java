package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class MenuInput {
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    void login(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

    }

    @FXML
    void signup(ActionEvent event) throws IOException {

        root = FXMLLoader.load((getClass().getResource("signup.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

    }

}
