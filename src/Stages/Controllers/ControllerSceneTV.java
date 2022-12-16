package Stages.Controllers;

import Stages.AudioPlayer;
import Stages.Objets;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSceneTV extends ControllerMaster implements Initializable {

    ////////////////////////////////////////////////////// attributs /////////////////////////////////////////////////////

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Initialisation de la scène <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private AnchorPane anchorPane;
    private final Objets ordonnanceComplete = new Objets("OrdonnanceReconstituée", 366, 140, 500, 665);

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Inventaire <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private VBox inventoryVisual;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> group TV <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private ImageView flecheRetourTV;
    @FXML
    private Label labelIndiceTv;

    //images
    @FXML
    private Label TVlabelM;
    @FXML
    private Label TVlabelD;
    @FXML
    private Label TVlabelG;
    @FXML
    private Button TVAllume;
    @FXML
    private ImageView teteRobot;
    @FXML
    private ImageView TVGrande;
    @FXML
    private ImageView TVGrise;
    @FXML
    private ImageView telTV;
    @FXML
    private ImageView ombre;

    //Autres variables
    private Timeline TempsTV = new Timeline();
    private int nbr = 0;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> group magazine <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private ImageView flecheRetourMagazine;
    @FXML
    private Label labelIndiceMagazine;
    @FXML
    private Button tournerPageDroite;
    @FXML
    private Button tournerPageGauche;

    //toutes les pages du magazine
    @FXML
    private ImageView page0;
    @FXML
    private ImageView page1;
    @FXML
    private ImageView page2;
    @FXML
    private ImageView page3;
    @FXML
    private ImageView page4;
    @FXML
    private ImageView page5;

    //images magazine
    @FXML
    private ImageView magazine;
    @FXML
    private ImageView pageDroiteMagazine;
    @FXML
    private ImageView pageGaucheMagazine;

    //autres variables
    private static int page = 0;
    boolean livreOuvert = false;


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> group table <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> scene Panneau <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private ImageView table;
    @FXML
    private ImageView fond;
    @FXML
    private ImageView retour;
    @FXML
    private Button ajouterBoutOrdonnanceH;
    @FXML
    private Button ajouterBoutOrdonnanceB;
    @FXML
    private ImageView boutOrdonnanceH;
    @FXML
    private ImageView boutOrdonnanceB;
    @FXML
    private Button reconstituer;
    @FXML
    private Label labelIndiceTable;

    //---------------------------------------------------- TV dans l'accueil --------------------------------------------------------------

    @FXML
    private Group sceneEnigmeTv;
    @FXML
    private ImageView TV;

    //---------------------------------------------------- magazine dans l'accueil --------------------------------------------------------------

    @FXML
    private Group sceneMagazine;
    @FXML
    private ImageView accesMagazine;


    ////////////////////////////////////////////////////// initalisation de la scène /////////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!stringExistsIn("reconstituer", "SceneTV.txt")){
            super.changePlan(anchorPane, "OrdonnanceReconstituée", "front");
        }
        super.initializeSceneObjects("SceneTV.txt", new Objets[]{ordonnanceComplete}, anchorPane, inventoryVisual);
        if(stringExistsIn("OrdonnanceReconstituée", "SceneTV.txt")){
            super.changePlan(anchorPane, "OrdonnanceReconstituée", "back");
        }
        else super.changePlan(anchorPane, "OrdonnanceReconstituée", "front");
        updateInventory(inventoryVisual);
    }


    public void switchScene(MouseEvent event) throws IOException {
        Node bouton= (Node) event.getSource();
        switch (bouton.getId()) {
            case "droite":
                super.switchScene("../Scenes/SalleOperation.fxml", event);
                break;
            case "gauche":
                super.switchScene("../Scenes/ScenePanneau.fxml", event);
                break;
            case "retour":
                super.switchScene("../Scenes/SceneTV.fxml", event);
                break;
            case "haut":
                super.switchScene("../Scenes/Plafond.fxml", event);
                break;
            default:
                super.switchScene("../Scenes/Menu.fxml",event);
                break;
        }
    }

    ////////////////////////////////////////////////////// Scene TV /////////////////////////////////////////////////////

    public void clicLabelIndice(MouseEvent event) {
        if(event.getSource() == labelIndiceTv)
            labelIndiceTv.toBack();

        if (event.getSource() == labelIndiceMagazine)
            labelIndiceMagazine.toBack();
    }

    //---------------------------------------------------- magazine dans l'accueil --------------------------------------------------------------

    //page de couverture
    public void ouvrirMagasine() {
        sceneMagazine.toFront();
    }

    //---------------------------------------------------- TV dans l'accueil --------------------------------------------------------------

    public void clicTV(MouseEvent mouseEvent) {

        if (!stringExistsIn("codeTele", "SceneTV.txt")){
            TVGrande.toFront();
            telTV.toFront();
        }
        else{
            if(mouseEvent.getSource() == TV && !CodeTVOK()){
                AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/television.wav"));
                audioPlayer.play();
                TVlabelD.setText("0");
                TVlabelM.setText("0");
                TVlabelG.setText("0");
                TVGrande.toFront();
                teteRobot.toFront();
                TVAllume.toFront();
            }
        }
        sceneEnigmeTv.toFront();
    }

    /////////////////////////////////////////////////// Group magazine /////////////////////////////////////////////////

    public void clicswitchMag(MouseEvent e){
        ImageView[] tabImages = new ImageView[]{page0,page1,page2,page3,page4,page5};

        if((e.getSource() == tournerPageGauche && page != 1) || (e.getSource() == tournerPageDroite && page != 5)) {
            tabImages[page].toBack();
            if(e.getSource() == tournerPageGauche && page != 1)
                page--;
            else
                page ++;
            magazine.toFront();
            tabImages[page].toFront();
            tournerPageDroite.toFront();
            tournerPageGauche.toFront();
        }

        if(e.getSource() == tournerPageGauche && page == 1) {
            pageGaucheMagazine.toBack();
            tournerPageGauche.toBack();
            magazine.toBack();
            page1.toBack();
            pageDroiteMagazine.toFront();
            page0.toFront();
            tournerPageDroite.toFront();
            page = 0;
        }

        if(e.getSource() == tournerPageDroite && page == 5) {
            tournerPageDroite.toBack();
            pageDroiteMagazine.toBack();
            magazine.toBack();
            page4.toBack();
            pageGaucheMagazine.toFront();
            page5.toFront();
            tournerPageGauche.toFront();
            page = 5;
        }
    }

    public void flecheRetourMagazine() {
        ImageView[] tabImages = new ImageView[]{page0,page1,page2,page3,page4,page5};
        tabImages[page].toBack();
        page=0;
        magazine.toBack();
        tournerPageGauche.toBack();
        pageGaucheMagazine.toBack();
        pageDroiteMagazine.toFront();
        page0.toFront();
        tournerPageDroite.toFront();
        sceneMagazine.toBack();
    }

    /////////////////////////////////////////////////// Group TV /////////////////////////////////////////////////

    public void flecheRetourTV() {
            sceneEnigmeTv.toBack();
    }

    public void TVallume() {
        TVGrise.toFront();
        TVlabelD.toFront();
        TVlabelG.toFront();
        TVlabelM.toFront();
    }

    public void ChangerNumTV(MouseEvent event) {
        Label label = (Label) event.getSource();
        if(label.getText().equals("9")) {
            nbr = 0;
        }
        else{
            nbr = Integer.parseInt(label.getText());
            nbr ++;
        }
        label.setText(""+nbr);
        if (CodeTVOK())
            codeBon();
    }

    public boolean CodeTVOK(){
        return TVlabelG.getText().equals("7") && TVlabelM.getText().equals("3") && TVlabelD.getText().equals("6");
    }

    public void codeBon(){
        changeState("SceneTV.txt", "codeTele");
        telTV.toFront();
        labelIndiceTv.toFront();
        labelIndiceTv.setText("Que peuvent signifier ces lettres et ce téléphone... ?");
    }

    /////////////////////////////////////////////////// Table /////////////////////////////////////////////////

    public void zoomTable(MouseEvent event){
        if (event.getSource() == table) {
            fond.toFront();
            ajouterBoutOrdonnanceB.toFront();
            ajouterBoutOrdonnanceH.toFront();
            reconstituer.toFront();
            retour.toFront();
            ombre.toFront();
            inventoryVisual.toFront();
            if (!stringExistsIn("reconstituer", "SceneTV.txt")) {
                super.changePlan(anchorPane, "OrdonnanceReconstituée", "front");
                ajouterBoutOrdonnanceH.toBack();
                ajouterBoutOrdonnanceB.toBack();
                reconstituer.toBack();
            }
            else{
                if (!stringExistsIn("ajouterBoutOrdonnanceH", "SceneTV.txt")) {
                    boutOrdonnanceH.toFront();
                    ajouterBoutOrdonnanceH.toBack();
                }
                if (!stringExistsIn("ajouterBoutOrdonnanceB", "SceneTV.txt")) {
                    boutOrdonnanceB.toFront();
                    ajouterBoutOrdonnanceB.toBack();
                }
            }
        }
    }

    public void reconstituerOrdonnance(MouseEvent event) {
        if (isSelected("ordonnance1") && event.getSource() == ajouterBoutOrdonnanceB) {
            try {
                useObject(event, inventoryVisual);
                super.changeObjectStatus(event, "SceneTV.txt", anchorPane, false, inventoryVisual);
                boutOrdonnanceB.toFront();
                ajouterBoutOrdonnanceB.toBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (isSelected("boutOrdonnance") && event.getSource() == ajouterBoutOrdonnanceH) {
            try {
                useObject(event, inventoryVisual);
                super.changeObjectStatus(event, "SceneTV.txt", anchorPane, false, inventoryVisual);
                boutOrdonnanceH.toFront();
                ajouterBoutOrdonnanceH.toBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!super.stringExistsIn("ajouterBoutOrdonnanceH", "SceneTV.txt") && !super.stringExistsIn("ajouterBoutOrdonnanceH", "SceneTV.txt") && event.getSource() == reconstituer) {
            try {
                super.changeObjectStatus(event, "SceneTV.txt", anchorPane, false, inventoryVisual);
                super.changePlan(anchorPane, "OrdonnanceReconstituée", "front");
                boutOrdonnanceB.toBack();
                boutOrdonnanceH.toBack();
                labelIndiceTable.toFront();
                reconstituer.toBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
