package Stages.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSceneToilettes extends ControllerMaster implements Initializable {

    ///////////////////////////////////////////////////////////////// attributs /////////////////////////////////////////////////////

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> scene toilette <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //---------------------------------------------------- mirroir dans la scène des toilettes --------------------------------------------------------------

    @FXML
    private Button miroir;
    @FXML
    private Group GroupeMirroir;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Inventaire <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private VBox inventoryVisual;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> groupe toilette mirroir <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private ImageView imageMirroirOrdonnance;
    @FXML
    private Label ordonnaneEnigme;
    @FXML
    private ImageView lavabo;
    @FXML
    private Button boutonOrdonnance;
    @FXML
    private ImageView mirroirSimple;
    @FXML
    private ImageView flecheSortie;

    //////////////////////////////////////////////////////////// Initialisation de la scène /////////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!stringExistsIn("imageMirroirOrdonnance", "SceneToilettes.txt")){
            boutonOrdonnance();
        }
        updateInventory(inventoryVisual);
    }

    public void switchScene(MouseEvent event) throws IOException {
        Node bouton = (Node) event.getSource();

        if(bouton.getId().equals("retournemenu")){
            super.switchScene("../Scenes/Menu.fxml",event);
        }else if(bouton.getId().equals("bas")){
            super.switchScene("../Scenes/ScenePanneau.fxml", event);
        }
    }

    ///////////////////////////////////////////////////////////////// Scene Toilette ////////////////////////////////////////////////////////////////

    public void afficherMiroir(MouseEvent mouseEvent){
        if(mouseEvent.getSource()==miroir){
            GroupeMirroir.toFront();
            inventoryVisual.toFront();
        }
    }

    //////////////////////////////////////////////////////////////// Groupe mirroir //////////////////////////////////////////////////////////////////////////

    public void retourToilettes() {
        GroupeMirroir.toBack();
    }


    public void useObject(MouseEvent event) throws IOException {
        if (isSelected("OrdonnanceReconstituée")) {
            super.useObject(event, inventoryVisual);
            imageMirroirOrdonnance.toFront();
            boutonOrdonnance.toFront();
            lavabo.toFront();
            flecheSortie.toFront();
        }
        changeState("SceneToilettes.txt", "imageMirroirOrdonnance");
    }

    public void boutonOrdonnance() {
        mirroirSimple.toFront();
        ordonnaneEnigme.toFront();
        lavabo.toFront();
        flecheSortie.toFront();
    }

}
