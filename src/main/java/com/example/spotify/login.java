package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

public class login {

    @FXML
    private ImageView hidden;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    void username(ActionEvent event) {

    }
    @FXML
    void hidden(ActionEvent event) {
        if (hidden.isPressed()) {
            password.setVisible(false);
        } else {
            password.setVisible(true);
        }
    }

}
