package Stages.Controllers;

import Stages.AudioPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSalleOperation extends ControllerMaster implements Initializable {

    ////////////////////////////////////////////////////// attributs /////////////////////////////////////////////////////

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Salle d'opération <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private Label labelPorteOperation;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Inventaire <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private VBox inventoryVisual;

    //////////////////////////////////////////////// Initialisation de la scène /////////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateInventory(inventoryVisual);
    }

    public void switchScene(MouseEvent event) throws IOException {
        Node bouton= (Node) event.getSource();
        if (bouton.getId().equals("droite"))
            super.switchScene("../Scenes/Accueil.fxml", event);
        else if(bouton.getId().equals("gauche"))
            super.switchScene("../Scenes/SceneTV.fxml", event);
        else if (bouton.getId().equals("haut"))
            super.switchScene("../Scenes/Plafond.fxml", event);
        else if(bouton.getId().equals("retournemenu")){
            super.switchScene("../Scenes/Menu.fxml",event);
        }
    }

    //////////////////////////////////////////////// Salle d'opération  /////////////////////////////////////////////////////


    public void labelPorteOperation() {
            labelPorteOperation.toBack();
    }

    public void PorteOperation(MouseEvent event) {
        if(isSelected("cleeCoffre"))
            labelPorteOperation.toFront();
        if(isSelected("badge")) {
            AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/porte.wav"));
            audioPlayer.play();


            try {
                super.switchScene("../Scenes/SceneChoixFin.fxml", event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
