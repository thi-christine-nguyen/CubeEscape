package Stages.Controllers;

import Stages.*;


import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerAccueil extends ControllerMaster implements Initializable {

    ////////////////////////////////////////////////////// attributs /////////////////////////////////////////////////////

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Initialisation de la scène <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    @FXML
    private AnchorPane anchorPane;
    private final Objets stylo = new Objets("stylo", 151, 505, 49,65);
    private final Objets ordonnance = new Objets("boutOrdonnance", 977, 730, 56, 74, "Oh ! J'ai trouvé un bout d'ordonnance.\nOn dirait qu'il y a écrit le nom de ma fille...\nIl faut que je trouve l'autre et que je la reconstitue");
    private final Objets badge = new Objets("badge", 801, 473, 88,68);
    private final Objets cle = new Objets("cleeCoffre", 580, 500, 45,74);

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Inventaire <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private VBox inventoryVisual;


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Accueil <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private ImageView gelHydroalcoolique;
    @FXML
    private Label dialogueE;
    @FXML
    private ImageView gauche;
    @FXML
    private ImageView droite;
    @FXML
    private ImageView haut;

    //---------------------------------------------------- telephone dans l'accueil --------------------------------------------------------------

    @FXML
    private ImageView telephoneAccueil;
    AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/VoixTelephone.wav"));

    //--------------------------------------------------- robot dans l'accueil ------------------------------------------------------------

    @FXML
    private ImageView robot;
    @FXML
    private Group clavierRobot;
    @FXML
    private TextField labelClavierRobot;

    //------------------------------------------------------ coffre dans l'accueil  --------------------------------------------------------------------

    @FXML
    private ImageView coffre;
    @FXML
    private Group coffreVisual;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Group Telephone <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private Group telephoneVisual;
    @FXML
    private TextField numeroTel;

    private final Code telephone = new Code();

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Group clavier Robot <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private final Code clavRobot = new Code();

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Group coffre <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private TextField numCoffre;
    private final Code code = new Code();

    //////////////////////////////////////////////// Initialisation de la scène /////////////////////////////////////////////////////


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Objets[] objets = new Objets[]{stylo, ordonnance, badge, cle};
        super.initializeSceneObjects("Accueil.txt", objets, anchorPane, inventoryVisual);
        if (!super.stringExistsIn("gelHydroalcoolique", "Accueil.txt")) {
            droite.toFront();
            gauche.toFront();
            haut.toFront();
            gelHydroalcoolique.setOnMouseClicked(null);
            dialogueE.toBack();
        }
        if(super.stringExistsIn("badge", "Accueil.txt")){
            super.changePlan(anchorPane, "badge", "back");
        }
        if(super.stringExistsIn("cleeCoffre", "Accueil.txt")){
            super.changePlan(anchorPane, "cleeCoffre", "back");
        }
        if(!super.stringExistsIn("telephone", "Accueil.txt")) {
            telephoneAccueil.setOnMouseClicked(event -> {
                audioPlayer.play();
            });

        }
        if(!super.stringExistsIn("enter", "Accueil.txt")){
            robot.setOnMouseClicked(null);
            changePlan(anchorPane, "cleeCoffre", "front");
        }
        if(!super.stringExistsIn("validerCoffre", "Accueil.txt")){
            coffre.setOnMouseClicked(null);
            super.changePlan(anchorPane, "badge", "front");
        }
        updateInventory(inventoryVisual);
    }

    public void switchScene(MouseEvent event) throws IOException {
        Node bouton = (Node) event.getSource();
        switch (bouton.getId()) {
            case "droite":
                super.switchScene("../Scenes/ScenePanneau.fxml", event);
                break;
            case "gauche":
                super.switchScene("../Scenes/SalleOperation.fxml", event);
                break;
            case "haut":
                super.switchScene("../Scenes/Plafond.fxml", event);
                break;
            default:
                switchScene("../Scenes/Menu.fxml", event);
                audioPlayer.arret();
                break;
        }
    }

    ////////////////////////////////////////////////////////// Accueil //////////////////////////////////////////////////////////

    @FXML
    public void clicGelHydroalcoolique(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == gelHydroalcoolique) {
            droite.toFront();
            gauche.toFront();
            haut.toFront();
            dialogueE.setText("Ah c'est bon !\nMaintenant que j'ai mis du gel hydroalcoolique,\nje peux rentrer.");
            dialogueE.toFront();
            super.changeObjectStatus(mouseEvent, "Accueil.txt", anchorPane, false, inventoryVisual);
            AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/getHydroalcoolique.wav"));
            audioPlayer.play();
        }

        if (mouseEvent.getSource() == dialogueE) {
            dialogueE.toBack();
        }
    }

    //--------------------------------------------------- telephone dans l'accueil ------------------------------------------------------------

    public void afficherTelephone() {
        if(numeroTel.getText().equals("7268")){
            AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/VoixTelephone.wav"));
            audioPlayer.play();
        }
        else {
            telephoneVisual.toFront();
            inventoryVisual.toFront();
        }
    }

    //--------------------------------------------------- robot dans l'accueil ------------------------------------------------------------

    @FXML
    public void clicBoutonRobot() {
            clavierRobot.toFront();
    }

    //--------------------------------------------------- coffre dans l'accueil ------------------------------------------------------------

    @FXML
    public void ouvrirCoffre() {
            coffreVisual.toFront();
            inventoryVisual.toFront();
    }

    ///////////////////////////////////////////////////////// telephone visual ////////////////////////////////////////////////////////////////////

    @FXML
    public void saisir(MouseEvent event) {
        AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/toucheTel.wav"));
        audioPlayer.play();
        Button button = (Button) event.getSource();
        telephone.saisir(button.getText());
        numeroTel.setText(telephone.getSaisie());
    }

    @FXML
    public void effacer() {
        telephone.effacer();
        numeroTel.setText(telephone.getSaisie());
    }

    @FXML
    public void appeler(MouseEvent event) throws IOException {
        if(numeroTel.getText().equals("7268")){
            telephoneVisual.toBack();
            telephoneAccueil.setOnMouseClicked(event1 -> {
                audioPlayer.play();
            });
            changeState("Accueil.txt", "telephone");
            audioPlayer.play();

        }
    }

    public void raccrocher() {
        telephoneVisual.toBack();
    }

    /////////////////////////////////////////////////////// clavier Robot /////////////////////////////////://////////////////

    public void flecheRetour() {
            clavierRobot.toBack();
    }

    @FXML
    public void clicClavier(MouseEvent event) {
        AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/clavierRobot.wav"));
        audioPlayer.play();

        labelClavierRobot.setStyle("-fx-text-fill: black;");
        Button button = (Button) event.getSource();

        clavRobot.saisir(button.getText());
        labelClavierRobot.setText(clavRobot.getSaisie());
    }

    public void supprimer() {
        clavRobot.effacer();
        labelClavierRobot.setText(clavRobot.getSaisie());
    }

    public void enter(MouseEvent event) throws IOException {
        if(labelClavierRobot.getText().equals("epilepsie")){
            clavierRobot.toBack();
            dialogueE.setText("Ah très bien, merci de l'information. Je vous met une clé sur le bureau,\nvous pourriez en avoir besoin...");
            dialogueE.toFront();
            super.changePlan(anchorPane, "cleeCoffre", "front");
            super.changeObjectStatus(event, "Accueil.txt", anchorPane, false, inventoryVisual);

            AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/valide.wav"));
            audioPlayer.play();
            robot.setOnMouseClicked(null);

        }
        else{
            labelClavierRobot.setText("Désolé mais je ne sais pas quoi vous dire...");
            labelClavierRobot.setStyle("-fx-text-fill: #ff0000;");
        }
    }

    /////////////////////////////////////////////////////////////// coffre visual ////////////////////////////////:////////////////////////////

    @FXML
    public void coffreCode(MouseEvent event) {
        AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/toucheCoffre.wav"));
        audioPlayer.play();
        Button button = (Button) event.getSource();
        code.saisir(button.getText());
        numCoffre.setText(code.getSaisie());
    }

    @FXML
    public void effacerSaisie() {
        code.effacer();
        numCoffre.setText(code.getSaisie());
    }

    @FXML
    public void validerCode(MouseEvent mouseEvent) throws IOException {
        if(numCoffre.getText().equals("8035")){
            super.changeObjectStatus(mouseEvent, "Accueil.txt", anchorPane, false, inventoryVisual);
            coffre.setOnMouseClicked(null);
            coffreVisual.toBack();
            super.changePlan(anchorPane, "badge", "front");
            AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/valide.wav"));
            audioPlayer.play();

        }
    }

    public void quitter() {
        coffreVisual.toBack();
    }

    public void useObject(MouseEvent event) throws IOException {
        super.useObject(event, inventoryVisual);
    }

}



