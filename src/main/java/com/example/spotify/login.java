package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.example.spotify.DataBase.User;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class login {

    @FXML
    private ImageView hidden;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userId;
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();


    @FXML
    void login(ActionEvent event)  throws IOException, SQLException {

        User user = new User(userId.getText(),password.getText());

        //if (user.)
        // Get a reference to the current stage
        Stage currentStage = (Stage) login.getScene().getWindow();

        // Close the current stage
        currentStage.close();

       /* User recentUser = new User(username.getText(),"",password.getText());*/
        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }


}
