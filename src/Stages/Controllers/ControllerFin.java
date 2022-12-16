package Stages.Controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class ControllerFin extends ControllerMaster{

    @FXML
    private Label TexteFin;
    @FXML
    private ImageView FondEcranFin;
    @FXML
    private ImageView Fin;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button retournemenu;

    @FXML
    public void clicEcranFin(MouseEvent e){
        if(e.getSource() == Fin){
            makeFadeOut();
            FondEcranFin.toFront();
            TexteFin.toFront();
            retournemenu.toFront();
        }

    }

    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(2000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0.60);
        //fadeTransition.setOnFinished((ActionEvent event) -> loadNextImage());
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }

    public void switchScene(MouseEvent event) throws IOException {
        Node bouton= (Node) event.getSource();
        super.switchScene("../Scenes/Menu.fxml",event);
    }

    /*
    private void loadNextImage(){
        FondEcranFin.toFront();
        TexteFin.toFront();
    }

     */




}
