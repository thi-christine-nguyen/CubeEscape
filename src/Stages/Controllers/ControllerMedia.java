package Stages.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerMedia extends ControllerMaster implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;
    private Button button;
    private String nameScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path = new File("src/Stages/Videos/intro.mp4").getAbsolutePath();
        Media media;
        if (stringExistsIn("cinematiqueDebut", "media.txt") && !stringExistsIn("intro", "media.txt")) {
            path = new File("src/Stages/Videos/cinematique.mp4").getAbsolutePath();
            media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnEndOfMedia(this::change);
            mediaPlayer.setAutoPlay(true);
            nameScene = "Accueil";
            changeState("media.txt", "cinematiqueDebut");
        } else if (stringExistsIn("intro", "media.txt")) {
            media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            nameScene = "Menu";
            mediaPlayer.setOnEndOfMedia(this::change);
            mediaPlayer.setAutoPlay(true);
            changeState("media.txt", "intro");
        }
    }

    public void createSkipButton(){
        if(!anchorPane.getChildren().contains(button)){
            button = new Button();
            button.setId("bouton");
            button.setText("PASSER");
            button.setOnMouseClicked(this::changeScene);
            if(button.isPressed()) mediaPlayer.pause();
            button.setTranslateX(1200);
            button.setTranslateY(750);
            button.setStyle("-fx-background-color: black; -fx-cursor: hand; -fx-text-inner-color: WHITE ");
            anchorPane.getChildren().add(button);
        }
    }

    public void change(){
        Parent nouvelleSceneChargement = null;
        try {
            nouvelleSceneChargement = FXMLLoader.load(this.getClass().getResource("../Scenes/"+nameScene+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene nouvelleScene = new Scene(nouvelleSceneChargement);
        Stage fenetre = (Stage) (mediaView.getScene().getWindow());
        fenetre.setScene(nouvelleScene);
        fenetre.show();
    }

    public void changeScene(MouseEvent event){
        mediaPlayer.setOnEndOfMedia(null);
        mediaPlayer.stop();
        Parent nouvelleSceneChargement = null;
        try {
            nouvelleSceneChargement = FXMLLoader.load(this.getClass().getResource("../Scenes/"+ nameScene + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene nouvelleScene = new Scene(nouvelleSceneChargement);
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        fenetre.setScene(nouvelleScene);
        fenetre.show();
    }


}
