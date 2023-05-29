module com.example.spotify {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.spotify to javafx.fxml;
    exports com.example.spotify;
}