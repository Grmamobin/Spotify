package com.example.spotify;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.spotify.DataBase.User;

import java.io.IOException;

public class signUp {

    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    void email(ActionEvent event) {

    }

    @FXML
    void password(ActionEvent event) {

    }

    @FXML
    void hadAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void username(ActionEvent event) {

    }

}

