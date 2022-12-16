package Stages;

import java.net.URL;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.util.Duration;

import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;


public class Taquin {

    private Button[][] taquin;
    private Stage stage; //création de la scene
    private VBox root ; //fond*/
    private GridPane grid; //grille du jeu

    public Taquin(Button[][] taquin, Stage stage, VBox root, GridPane grid) {
        this.taquin = taquin;
        this.stage = stage;
        this.root = root;
        this.grid = grid;
    }

    public GridPane getGrid() {
        return grid;
    }



    public Button[][] getTaquin() {
        return taquin;
    }



    public void  gauche (Button[][] tab, int i, int j){
        tab[i][j].setText(tab[i][j+1].getText());
        tab[i][j+1].setText("");


    }

    public void droite (Button[][] tab, int i, int j){
        tab[i][j].setText(tab[i][j-1].getText());
        tab[i][j-1].setText("");

    }

    public void haut (Button[][] tab, int i, int j){
        tab[i][j].setText(tab[i+1][j].getText());
        tab[i+1][j].setText("");


    }

    public void bas (Button[][] tab, int i, int j) {
        tab[i][j].setText(tab[i-1][j].getText());
        tab[i-1][j].setText("");

    }

    public void affichage(){
            VBox.setMargin(grid, new Insets(50, 50, 50, 50)); //Contour du grid
            root.getChildren().addAll(grid); //ajoute la grille
            Scene scene = new Scene(root, 280, 265);//235, 260
            scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
            stage.setTitle("Résolvez le taquin !");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

    }


    public void melange ()
    {

        Random rand = new Random();
        int a = 2, b = 2, rd, k = 1;
        //[ligne][colonne]

        //Construire un taquin
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                taquin[i][j] = new Button();//met les boutons dans le grid
                taquin[i][j].setText(Integer.toString(k));
                taquin[i][j].setId("case");
                grid.add(taquin[i][j], j, i);
                taquin[i][j].setPrefHeight(60);
                taquin[i][j].setPrefWidth(60);

                k++;
            }

        }

        //mélanger le taquin
        for(k = 0; k < 1; k++) //nombre de mélange
        {
            if(a==0 && b==0) //coin haut gauche (2 choix de mouvements)
            {
                rd = rand.nextInt(2);
                if(rd==0) {
                    gauche(taquin, a, b);
                    b++;
                }
                if(rd==1) {
                    haut(taquin, a, b);
                    a++;
                }
            }
            else if(a==0 && b==2)//coin haut droit (2 choix de mouvements)
            {
                rd = rand.nextInt(2);
                if(rd==0) {
                    droite(taquin, a, b);
                    b--;
                }
                if(rd==1) {
                    haut(taquin, a, b);
                    a++;

                }
            }
            else if(a==2 && b==2)//coin bas droit(2 choix de mouvements)
            {
                rd = rand.nextInt(2);
                if(rd==0) {
                    droite(taquin, a, b);
                    b--;

                }
                if(rd==1) {

                    bas(taquin, a, b);
                    a--;

                }
            }
            else if(a==2 && b==0) //coin bas gauche(2 choix de mouvements)
            {
                rd = rand.nextInt(2);
                if(rd==0) {
                    gauche(taquin, a, b);
                    b++;

                }
                if(rd==1) {
                    bas(taquin, a, b);
                    a--;
                }
            }
            else if(a==1 && b==0) //bord milieu (3 choix de mouvements)
            {
                rd = rand.nextInt(3);
                if(rd==0) {
                    gauche(taquin, a, b);
                    b++;

                }
                if(rd==1) {
                    bas(taquin, a, b);
                    a--;

                }
                if(rd==2) {
                    haut(taquin, a, b);
                    a++;

                }
            }
            else if(a==0 && b==1)
            {
                rd = rand.nextInt(3);
                if(rd==0) {
                    gauche(taquin, a, b);
                    b++;


                }
                if(rd==1) {
                    droite(taquin, a, b);
                    b--;
                }

                if(rd==2) {
                    haut(taquin, a, b);
                    a++;
                }
            }
            else if(a==1 && b==2)
            {
                rd = rand.nextInt(3);
                if(rd==0) {
                    droite(taquin, a, b);
                    b--;
                }
                if(rd==1) {
                    bas(taquin, a, b);
                    a--;
                }
                if(rd==2) {
                    haut(taquin, a, b);
                    a++;

                }
            }
            else if(a==2 && b==1)
            {
                rd = rand.nextInt(3);
                if(rd==0) {
                    gauche(taquin, a, b);
                    b++;

                }
                if(rd==1) {
                    droite(taquin, a, b);
                    b--;
                }
                if(rd==2) {
                    bas(taquin, a, b);
                    a--;

                }
            }
            else //Carré du centre (4 mouvements possible)
            {
                rd = rand.nextInt(4);
                if(rd==0) {
                    gauche(taquin, a, b);
                    b++;

                }
                if(rd==1) {
                    droite(taquin, a, b);
                    b--;

                }
                if(rd==2) {
                    bas(taquin, a, b);
                    a--;
                }
                if(rd==3) {
                    haut(taquin, a, b);
                    a++;

                }
            }
        }
        taquin[a][b].setId("trou");


    }




    public boolean mouvement(int x, int y) {
        if (x > 0) {
            if (taquin[x - 1][y].getText().equals("")) {
                taquin[x - 1][y].setText(taquin[x][y].getText());
                taquin[x - 1][y].setId("case");
                taquin[x][y].setText("");
                taquin[x][y].setId("trou");


            }
        }
        if (x < 2) {
            if (taquin[x + 1][y].getText().equals("")) {
                taquin[x + 1][y].setText(taquin[x][y].getText());
                taquin[x + 1][y].setId("case");
                taquin[x][y].setText("");
                taquin[x][y].setId("trou");


            }
        }
        if (y > 0) {
            if (taquin[x][y - 1].getText().equals("")) {
                taquin[x][y - 1].setText(taquin[x][y].getText());
                taquin[x][y - 1].setId("case");
                taquin[x][y].setText("");
                taquin[x][y].setId("trou");


            }
        }
        if (y < 2) {
            if (taquin[x][y + 1].getText().equals("")) {
                taquin[x][y + 1].setText(taquin[x][y].getText());
                taquin[x][y + 1].setId("case");
                taquin[x][y].setText("");
                taquin[x][y].setId("trou");


            }
        }
        return estGagnant();
    }


    public boolean estGagnant()
    {

        int i, j, k = 1; //k=id
        boolean estResolu = true;

        //parcours du taquin
        for(i = 0; i < 3; i++) {
            for(j = 0; j < 3; j++, k++)
            {
                if(k == 9)
                {
                    if(!taquin[i][j].getText().equals("")) {
                        estResolu = false;
                    }
                }
                else
                {
                    if(!taquin[i][j].getText().equals(Integer.toString(k))) {
                        estResolu = false;
                    }
                }

            }
        }
        if(estResolu){
            stage.close();
            gagne();
        }
        return estResolu;




    }

    public void gagne ()
    {

        URL imageURL = getClass().getResource("Images/taquinFin.jpg");
        Image image = new Image(imageURL.toExternalForm());
        ImageView imageView = new ImageView(image);

        Stage finishStage = new Stage();
        Pane pane = new Pane();
        pane.getChildren().setAll(imageView);
        Scene finishScene = new Scene(pane, 800, 560);
        finishStage.setTitle("Bravo ! Le taquin est résolu");

        finishStage.setScene(finishScene);
        finishStage.show();

        //Ferme la fenetre du taquin au bout de 10 secondes
        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished( event1 -> finishStage.close() );
        delay.play();




    }



}