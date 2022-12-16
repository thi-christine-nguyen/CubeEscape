package Stages.Controllers;

import Stages.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class ControllerScenePanneau extends ControllerMaster implements Initializable {

    ////////////////////////////////////////////////////// attributs /////////////////////////////////////////////////////

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Initialisation de la scène <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private AnchorPane anchorPane;
    private final Objets ordonnance = new Objets("ordonnance1", 457, 284, 56, 74, "Bien ! Il faut que je trouve un meuble où reconstituer\ncette ordonnance");

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Inventaire <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @FXML
    private VBox inventoryVisual;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Groupe Radio <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private Label labelHeure;
    @FXML
    private ImageView retour;
    @FXML
    private ImageView radioFinie;
    @FXML
    private Label labelIndiceRadio;

    private int NbrClic = 0;
    private Timeline TIMER = new Timeline();
    private int minute = LocalTime.now().getMinute();
    private int heure = LocalTime.now().getHour();
    private final LocalTime horaire = LocalTime.now();
    private final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("hh : mm a");

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Scène Panneau <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public static boolean porteToilettesOuvertes = false;
    @FXML
    private Label labelIndice;

    //------------------------------------------------ radio dans la scène panneau --------------------------------------------------
    @FXML
    private ImageView radioPetite;
    @FXML
    private Group Radio;

    //------------------------------------------------ armoires de la scène panneau --------------------------------------------------

    @FXML
    private ImageView armoireFermeeH;
    @FXML
    private ImageView armoireOuverteH;
    @FXML
    private ImageView armoireFermeeB;
    @FXML
    private ImageView armoireOuverteB;

    ////////////////////////////////////////////////////// Initialisation de la scène /////////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!stringExistsIn("armoireFermeeH", "ScenePanneau.txt")){
            armoireOuverteH.toFront();
            armoireFermeeH.toBack();
        }
        if(!super.stringExistsIn("radioEnToFront", "ScenePlafond.txt")){
            radioPetite.toFront();
        }
        if(!stringExistsIn("taquin", "ScenePanneau.txt")){
            armoireFermeeB.toBack();
            armoireOuverteB.toFront();
        }
        super.initializeSceneObjects("ScenePanneau.txt", new Objets[]{ordonnance}, anchorPane, inventoryVisual);
        if(stringExistsIn("taquin", "ScenePanneau.txt")){
            super.changePlan(anchorPane, "ordonnance1", "back");
        }
        else super.changePlan(anchorPane, "ordonnance1", "front");
        updateInventory(inventoryVisual);
    }


    public void switchScene(MouseEvent event) throws IOException {
        Node bouton = (Node) event.getSource();
        if (bouton.getId().equals("droite")) {
            super.switchScene("../Scenes/SceneTV.fxml", event);
        }
        else if (bouton.getId().equals("gauche")) {
            super.switchScene("../Scenes/Accueil.fxml", event);
        }
        else if (bouton.getId().equals("retour")) {
            super.switchScene("../Scenes/ScenePanneau.fxml", event);
        }
        else if(bouton.getId().equals("haut")) {
            super.switchScene("../Scenes/Plafond.fxml", event);
        }
        else if(bouton.getId().equals("toilettes") && isSelected("cleeCoffre") || porteToilettesOuvertes){
            AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/cle.wav"));
            audioPlayer.play();
            try{
                useObject(event, inventoryVisual);
                porteToilettesOuvertes = true;
            }
            catch (IOException e){
                e.printStackTrace();
            }
            super.switchScene("../Scenes/SceneToilettes.fxml", event);
        }
        else if(bouton.getId().equals("retournemenu")){
            super.switchScene("../Scenes/Menu.fxml",event);
        }
        else{
            labelIndice.toFront();
        }

    }
    ////////////////////////////////////////////////////// scène panneau //////////////////////////////////////////////////////////////////////

    //--------------------------------------------------- radio dans la scène panneau ------------------------------------------------------------

    public void afficherRadio(){
        Radio.toFront();
        labelHeure.setText("" + horaire.format(dtf2));
    }

    ////////////////////////////////////////////////////////// Groupe Radio /////////////////////////////////////////////////////////////////////

    public void clicLabelIndice() {
        labelIndiceRadio.toBack();
    }

    public void ajouterTemps(){

        if(NbrClic == 1)
            heure++;
        if(NbrClic == 2)
            minute++;

        horsDuTemps();
        labelHeure.setText(LocalTime.of(heure, minute).format(dtf2));

    }

    public void enleverTemps(){

        if(NbrClic == 1)
            heure--;
        if(NbrClic == 2)
            minute--;

        horsDuTemps();
        labelHeure.setText(LocalTime.of(heure, minute).format(dtf2));

    }

    public void horsDuTemps(){
        if(heure == -1)
            heure = 23;

        if(heure == 24)
            heure = 0;

        if(minute == 60)
            minute = 0;

        if(minute == -1)
            minute = 59;
    }

    public void changementDeChiffre(){
        NbrClic ++;

        if(NbrClic != 3){
            //affichageHoraire();
            TempsChangementHoraire();
            labelHeure.setStyle("-fx-text-fill: white;");
        }

        if(NbrClic == 3){
            TempsChangementHoraire();
            TIMER.stop();
            if(heure == 14 && minute == 30 ){
                radioFinie.toFront();
                labelHeure.toFront();
                labelIndiceRadio.toFront();
                retour.toFront();
                AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/valide.wav"));
                audioPlayer.play();
                changeState("ScenePlafond.txt", "code");
            }
            NbrClic = 0;

        }
    }

    //clignottement
    public void TempsChangementHoraire(){
        TIMER = new Timeline(
                new KeyFrame(Duration.seconds(2), ae->ChangeColor1()),
                new KeyFrame(Duration.seconds(1),ae->ChangeColor2())
        );
        TIMER.setCycleCount(Animation.INDEFINITE);
        TIMER.setAutoReverse(true);
        TIMER.play();
    }

    public void ChangeColor1(){
        if (NbrClic == 1)
            labelHeure.setStyle("-fx-text-fill: black;");
        else
            labelHeure.setStyle("-fx-text-fill: white;");
    }

    public void ChangeColor2(){
        labelHeure.setStyle("-fx-text-fill: white;");
    }

    ////////////////////////////////////////////////////////////////////////// Armoires //////////////////////////////////////////////////

    public void agirSurArmoire(MouseEvent event) throws IOException {
        if (event.getSource() == armoireFermeeH) {
            armoireOuverteH.toFront();
            armoireFermeeH.toBack();
            AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/armoire.wav"));
            audioPlayer.play();
        }
        radioPetite.toFront();
        changeState("ScenePlafond.txt", "radioEnToFront");
        super.changeObjectStatus(event, "ScenePanneau.txt", anchorPane, false, inventoryVisual);
    }

    ////////////////////////////////////////////////////////////////////////// Taquin //////////////////////////////////////////////////

// Nouvelle fonction sur chaque bouton
    //appliquer la nouvelle fonction dans tout les boutons de taquin
    //il faut transformer la position de event.getSource en x, y
    // dans cette focntion : appeler mouvement(x,y)
    //on parcours le grid pane de la scene pour savoir si bon
    // si bon : ecrire dans le fichier que taquin est résolu
    // faire toFront et toBack de l'armoire

    public void verif(Button b, Taquin taquin){
        int x=0, y=0;

        for(int i =0; i<3; i++) {

            if (b == taquin.getGrid().getChildren().get(i) || b == taquin.getGrid().getChildren().get(i + 3) || b == taquin.getGrid().getChildren().get(i + 6)) {

                if(i==1) {
                    y = 1;
                }

                if(i==2) {
                    y = 2;
                }



                if(b == taquin.getGrid().getChildren().get(i + 3)) {
                    x = 1;
                }

                else {
                    x = 2;
                }
            }
        }


        if(taquin.mouvement(x,y)){
            changeState("ScenePanneau.txt", "taquin");
            armoireFermeeB.toBack();
            armoireOuverteB.toFront();
            AudioPlayer audioPlayer = new AudioPlayer(new File("src/Stages/Sons/armoire.wav"));
            audioPlayer.play();
            super.changePlan(anchorPane, "ordonnance1", "front");
        }else{
            taquin.mouvement(x,y);
        }
    }

    public void ouvrirTaquin(MouseEvent event) {

        Button[][] tab = new Button[3][3];
        Stage stage = new Stage(); //création de la scene
        VBox root = new VBox(); //fond
        root.setId("root");
        GridPane grid = new GridPane(); //grille du jeu

        Taquin jeu = new Taquin(tab, stage, root, grid);

        if (event.getSource() == armoireFermeeB) {
            jeu.affichage();

            jeu.melange();

            //Mouvement des boutons
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int x = i, y = j;
                    jeu.getTaquin()[i][j].setOnAction(e -> verif(jeu.getTaquin()[x][y], jeu));
                }
            }
        }
    }
}

