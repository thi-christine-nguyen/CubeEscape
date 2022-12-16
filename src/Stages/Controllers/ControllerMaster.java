package Stages.Controllers;

import Stages.Inventaire;
import Stages.Objets;
import Stages.Parser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public abstract class ControllerMaster {

    private final Parser p = new Parser();

    private final Inventaire inventaire = new Inventaire();


    public void switchScene(String s, MouseEvent event) throws IOException {
        Parent nouvelleSceneChargement = FXMLLoader.load(this.getClass().getResource(s));
        Scene nouvelleScene = new Scene(nouvelleSceneChargement);
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        fenetre.setScene(nouvelleScene);
        fenetre.show();
    }

    public void retournerAuMenu(MouseEvent event) throws IOException{
        switchScene("../Scenes/Menu.fxml", event);
    }

    //Teste si un objet est selectionné. Plusieurs cas possibles ;
    //cas 1 : objet est déjà selectionné, on déselectionne tout et on retourne null
    //cas 2 : objet pas selectionné, on le selectionne et on retourne son id
    //cas 3 : l'objet passé en paramètre n'a pas d'image, on renvoie null
    @FXML
    public String objetSelectionne(MouseEvent event) throws IOException {
        ImageView imageView = (ImageView)event.getSource();
        if(imageView.getImage() != null){

            for (Node n : imageView.getParent().getChildrenUnmodifiable()){
                ImageView i = (ImageView)n;
                i.setEffect(null);
            }
            if(inventaire.isSelected(((Node)event.getSource()).getId())){
                inventaire.notSelectAll();
                return null;
            }
            inventaire.notSelectAll();
            p.writeIn("inventaire.txt", ((Node)event.getSource()).getId());
            imageView.setEffect(new DropShadow(20, Color.BLACK));
            return ((Node) event.getSource()).getId();

        }
        return null;
    }

    //vérifie si l'objet objectName est selectionné dans l'inventaire
    public boolean isSelected(String objectName){
        return inventaire.isSelected(objectName);
    }

    //Marque dans le fichier fileName que l'objet objectName a été pris
    public void changeState(String fileName, String objectName){
        p.writeIn(fileName, objectName);
    }

    //Marque dans le fichier de nom sceneFile que l'objet a été pris.
    //anchorPane : l'Anchorpane de la scène courante.
    //addInventory : booleen specifiant l'ajout de l'objet à l'inventaire ou non
    public void changeObjectStatus(MouseEvent event, String sceneFile, AnchorPane anchorPane, boolean addInventory, VBox inventoryVisual) throws IOException {
        changeState(sceneFile, ((Node) event.getSource()).getId());
        if(addInventory){
            anchorPane.getChildren().remove(event.getSource());
            inventaire.addInventory(((Node) event.getSource()).getId());
            updateInventory(inventoryVisual);
        }

    }

    //Vérifie si le nom de l'objet passé en paramètre est bien dans le fichier de nom nomFichier.
    //true : l'objet n'a pas été pris
    //false : l'objet a été pris
    public boolean stringExistsIn(String objectName, String fileName){
        for (String s: p.readIn(fileName) ){
            if(s.equals(objectName)) return true;
        }
        return false;
    }

    //Initialise tous les objets de la scène avec lequels le joueur interagit et les ajoute dans l'anchorPane de la scène passé en paramètre.
    //sceneFileName : nom du fichier de la scène à modifier
    //sceneObjects : tableau des différents objets de la scène
    //anchorPane : anchorPane de la scène
    public void initializeSceneObjects(String sceneFileName, Objets[] sceneObjects, AnchorPane anchorPane, VBox inventoryVisual) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Objets o : sceneObjects) {
            if (stringExistsIn(o.getNom(), sceneFileName)) {
                URL imageURL = getClass().getResource("../Images/ObjetsCliquables/" + o.getNom() + ".png");
                Image image = new Image(imageURL.toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setId(o.getNom());
                imageView.setOnMouseClicked(event -> {
                    try {
                        changeObjectStatus(event, sceneFileName, anchorPane, true, inventoryVisual);
                        changePlan(anchorPane, o.getLabel().getId(), "front");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                imageView.setLayoutX(o.getPosX());
                imageView.setLayoutY(o.getPosY());
                imageView.setFitHeight(o.getHeight());
                imageView.setFitWidth(o.getWidth());
                if(o.getLabel().getId() != null){
                    o.getLabel().setOnMouseClicked(event -> {
                        changePlan(anchorPane, o.getLabel().getId(), "back");
                    });
                    anchorPane.getChildren().add(o.getLabel());
                    anchorPane.getChildren().get(anchorPane.getChildren().size()-1).toBack();
                }
                nodes.add(imageView);
            }
        }
        anchorPane.getChildren().addAll(nodes);
    }

    //Parcours les tableaux des Children de Anchorpane et change le plan (passé en paramètre) de l'objet dont le nom est passé en paramètre.
    public void changePlan(AnchorPane anchorPane, String nomObjet, String plan) {
        if (plan.equals("front") || plan.equals("back")) {
            for (Node n : anchorPane.getChildren()) {
                if (n.getId() != null) {
                    if (n.getId().equals(nomObjet)) {
                        if (plan.equals("front")) {
                            n.toFront();
                        } else n.toBack();
                        break;
                    }
                }
            }
        }
    }

    //Met à jour l'inventaire d'une scène passé en paramètre.
    public void updateInventory(VBox inventoryVisual){
        int i = 0;
        String[] strings = p.readIn("inventaire.txt");
        if(strings != null){
            for (String s: strings) {
                boolean selected = inventaire.isSelected(s);
                if(selected) s= s.replace("#", "");
                if(!s.equals("")){
                    URL imageURL = getClass().getResource("../Images/ObjetsCliquables/" + s + ".png");
                    Image image = new Image(imageURL.toExternalForm());
                    ImageView imageView = (ImageView) inventoryVisual.getChildren().get(i);
                    imageView.setImage(image);
                    imageView.setId(s);
                    imageView.setOnMouseClicked(event -> {
                        try {
                            objetSelectionne(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    if(selected) imageView.setEffect(new DropShadow(20, Color.BLACK));
                    inventoryVisual.getChildren().set(i, imageView);
                }
                i++;
            }
        }
    }

    //Vérifie si on peut utiliser un objet sur un autre. Si c'est le cas, on l'enlève de l'inventaire.
    public void useObject(MouseEvent event, VBox inventoryVisual) throws IOException {
        Node n = (Node) event.getSource();
        if (n.getAccessibleText() != null) {
            if (inventaire.isSelected(n.getAccessibleText())) {
                inventaire.removeInventory(n.getAccessibleText());
                for (Node nodes : inventoryVisual.getChildren()) {
                    if (nodes.getId() != null && nodes.getId().equals(n.getAccessibleText())) {
                        ImageView imageView = (ImageView) nodes;
                        imageView.setImage(null);
                        imageView.setId(null);
                        break;
                    }
                }
            }
        }
    }

    public void reset(String fileName) throws IOException {
        if(fileName.equals("inventaire.txt")) inventaire.resetInventory();
        else p.resetFile(fileName);
    }
}

