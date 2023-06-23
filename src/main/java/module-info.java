module com.example.spotify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires com.google.gson;
    requires javafx.media;
    requires java.desktop;

    opens com.example.spotify to javafx.fxml;
    opens com.example.spotify.DataBase to javafx.base;
    exports com.example.spotify;
}