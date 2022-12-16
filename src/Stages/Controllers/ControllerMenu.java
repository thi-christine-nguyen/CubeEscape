package Stages.Controllers;

import Stages.AudioPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu extends ControllerMaster implements Initializable {

    private  AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/ambiance.wav"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        audioPlayer.boucle();
    }

    @FXML
    public void accederAuJeu(MouseEvent event) throws IOException {
        audioPlayer.arret();
        if (stringExistsIn("cinematiqueDebut", "media.txt")){
            switchScene("../Scenes/Media.fxml", event);
        }
        else super.switchScene("../Scenes/Accueil.fxml", event);
    }

    @FXML
    public void quitterLeJeu(MouseEvent event) throws IOException {
        reset("media.txt");
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        fenetre.hide();
    }

    public void recommencerJeu(MouseEvent event) throws IOException {
        String[] files = new String[]{"Accueil.txt", "ScenePanneau.txt", "SceneTV.txt", "ScenePlafond.txt", "SceneToilettes.txt", "inventaire.txt", "media.txt"};
        for (String s: files) {
            reset(s);
        }
        changeState("media.txt", "intro");
        Button button = (Button) event.getSource();
        button.setText("RÉINITIALISÉ");
        button.setOnMouseClicked(null);

    }

}
