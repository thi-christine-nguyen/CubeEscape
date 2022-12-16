package Stages.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPlafond extends ControllerMaster implements Initializable {

    ////////////////////////////////////////////////////// attributs /////////////////////////////////////////////////////


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Initialisation de la scène <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private VBox inventoryVisual;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Plafond <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private Label horaire;

    ///////////////////////////////////////////////// Initialisation de la scène /////////////////////////////////////////////////////


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!super.stringExistsIn("code", "ScenePlafond.txt")){
            horaire.toFront();
        }
        updateInventory(inventoryVisual);
    }

    public void switchScene(MouseEvent event) throws IOException {
        Node bouton= (Node) event.getSource();
        if (bouton.getId().equals("droite"))
            super.switchScene("../Scenes/ScenePanneau.fxml", event);
        else if(bouton.getId().equals("gauche"))
            super.switchScene("../Scenes/SalleOperation.fxml", event);
        else if(bouton.getId().equals("haut"))
            super.switchScene("../Scenes/SceneTV.fxml", event);
        else if(bouton.getId().equals("retournemenu")){
            super.switchScene("../Scenes/Menu.fxml",event);
        }
        else
            super.switchScene("../Scenes/Accueil.fxml", event);
    }

}
