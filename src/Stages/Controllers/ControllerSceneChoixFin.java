package Stages.Controllers;


import Stages.AudioPlayer;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSceneChoixFin extends ControllerMaster implements Initializable {
    private AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/choix.wav"));

    //////////////////////////////////////////////// Initialisation de la scène /////////////////////////////////////////////////////

    public void initialize(URL url, ResourceBundle resourceBundle) {
        audioPlayer.boucle();
    }

    public void switchScene(MouseEvent event) throws IOException {
        Node button = (Node) event.getSource();
        if (button.getId().equals("bon")){
            audioPlayer.arret();
            super.switchScene("../Scenes/SceneBonChoixFin.fxml", event);

        }
        else if(button.getId().equals("retournemenu")){
            super.switchScene("../Scenes/Menu.fxml",event);
            audioPlayer.arret();
        }
        else {
            super.switchScene("../Scenes/SceneMauvaisChoixFin.fxml", event);
            audioPlayer.arret();
        }

    }

}
