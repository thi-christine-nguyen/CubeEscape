package Stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent fenetre = FXMLLoader.load(getClass().getResource("Scenes/Media.fxml"));
        primaryStage.setTitle("Game Cube - The Hospital");
        primaryStage.setScene(new Scene(fenetre, 1280, 800));
        primaryStage.show();

    }

}
