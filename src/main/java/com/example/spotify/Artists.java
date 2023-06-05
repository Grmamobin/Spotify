package com.example.spotify;
import com.example.spotify.DataBase.Artist;
import com.example.spotify.DataBase.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class Artists implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();
    @FXML
    private Button back;
    @FXML
    private GridPane artistsGrade;
    private List<Artist> artists = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        artists = new ArrayList<>(artists());
        int col = 0;
        int row = 1;
        try {
            for(int i = 0 ; i < artists.size() ; i++) {
                FXMLLoader  fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ArtistPic.fxml"));
                Pane box = fxmlLoader.load();
                ArtistPic pic = fxmlLoader.getController();
                pic.setData(artists.get(i));

                if (col == 3) {
                    col = 0;
                    ++row;
                }

                artistsGrade.add(box,col++,row);
                GridPane.setMargin(box,new Insets(10));
            }

        }catch (IOException e) {
            e.printStackTrace();
        }



    }
    private List<Artist> artists(){

        List<Artist> ls = new ArrayList<>();


        Artist artists = new Artist();
        artists.setProfile("Pictures/Ed.jpg");
        artists.setGenre("POP");
        artists.setTwitter("@edsheeran");
        artists.setInstagram("@teddysphotos");
        artists.setName("Ed sheeran");
        ls.add(artists);

        artists = new Artist();
        artists.setProfile("Pictures/SmartSelect_20230604_155923_Samsung Internet.jpg");
        artists.setGenre("Pop");
        artists.setTwitter("@ShawnMendes");
        artists.setInstagram("@shawnmendes");
        artists.setName("Shawn Mendes");
        ls.add(artists);

        artists = new Artist();
        artists.setProfile("Pictures/The Chainsmokers.jpg");
        artists.setGenre("Electropop");
        artists.setTwitter("@TheChainsmokers");
        artists.setInstagram("@thechainsmokers");
        artists.setName("The Chainsmokers");
        ls.add(artists);

        artists = new Artist();
        artists.setProfile("Pictures/SmartSelect_20230604_185628_Samsung Internet.jpg");
        artists.setGenre("Dark Pop");
        artists.setTwitter("@billieeilish");
        artists.setInstagram("@billieeilish");
        artists.setName("Billie Eilish");
        ls.add(artists);


        artists = new Artist();
        artists.setProfile("Pictures/Drake.jpg");
        artists.setGenre("Hip-Hop");
        artists.setTwitter("@Drake");
        artists.setInstagram("@champagnepapi");
        artists.setName("Drake");
        ls.add(artists);

        artists = new Artist();
        artists.setProfile("Pictures/conan.jpg");
        artists.setGenre("Pop indie");
        artists.setTwitter("@conangray");
        artists.setInstagram("@conangray");
        artists.setName("Conan Gray");
        ls.add(artists);

        artists = new Artist();
        artists.setProfile("Pictures/selena.jpg");
        artists.setGenre("POP");
        artists.setTwitter("@selenagomez");
        artists.setInstagram("@selenagomez");
        artists.setName("Selena Gomez");
        ls.add(artists);

        artists = new Artist();
        artists.setProfile("Pictures/eminem.jpg");
        artists.setGenre("Rap");
        artists.setTwitter("@Eminem");
        artists.setInstagram("@eminem");
        artists.setName("Eminem");
        ls.add(artists);

        artists = new Artist();
        artists.setProfile("Pictures/gaga.jpg");
        artists.setGenre("Dance Pop");
        artists.setTwitter("@Ladygaga");
        artists.setInstagram("@ladygaga");
        artists.setName("Lady Gaga");
        ls.add(artists);



        return ls;
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) back.getScene().getWindow();

        // Close the current stage
        currentStage.close();
        root = FXMLLoader.load(getClass().getResource("page.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
