package Stages;


import javafx.css.Stylesheet;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;

public class Objets{

    private final String nom;
    private final double posX;
    private final double posY;
    private final double height;
    private final double width;
    private final Label messageOnClicked = new Label();

    public Objets(String nom, double posX, double posY, double height, double width) {
        this.nom = nom;
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
    }

    public Objets(String nom, double posX, double posY, double height, double width, String message) {
        this.nom = nom;
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        initLabel(nom+"Label", message);
    }

    private void initLabel(String id, String message){
        messageOnClicked.setText(message);
        messageOnClicked.setId(id);
        messageOnClicked.setFont(Font.font("Xanh Mono", 24));
        messageOnClicked.setLayoutX(130);
        messageOnClicked.setLayoutY(50);
        messageOnClicked.setAlignment(Pos.CENTER);
        messageOnClicked.setPrefHeight(117);
        messageOnClicked.setPrefWidth(850);
        messageOnClicked.toBack();

    }

    public String getNom(){
        return nom;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Label getLabel() {
        return messageOnClicked;
    }
}
