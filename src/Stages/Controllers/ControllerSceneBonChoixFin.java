package Stages.Controllers;

import Stages.AudioPlayer;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSceneBonChoixFin extends ControllerMaster implements Initializable {
    private AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/bon.wav"));

    //////////////////////////////////////////////// Initialisation de la scène /////////////////////////////////////////////////////

    public void initialize(URL url, ResourceBundle resourceBundle) {
        audioPlayer.boucle();
    }

    public void switchScene(MouseEvent event) throws IOException {
        Node bouton = (Node) event.getSource();
        if (bouton.getId().equals("clicImage")) {
            super.switchScene("../Scenes/Fin.fxml", event);
        }
        else if(bouton.getId().equals("retournemenu")){
            super.switchScene("../Scenes/Menu.fxml",event);
        }
        audioPlayer.arret();
    }
}
