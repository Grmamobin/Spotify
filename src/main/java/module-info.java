module com.example.spotify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires com.google.gson;


    opens com.example.spotify to javafx.fxml;
    exports com.example.spotify;
    //exports com.example.spotify.Front_end;
    //opens com.example.spotify.Front_end to javafx.fxml;
    //exports com.example.spotify.Front_end;
    //opens com.example.spotify to javafx.fxml;

}