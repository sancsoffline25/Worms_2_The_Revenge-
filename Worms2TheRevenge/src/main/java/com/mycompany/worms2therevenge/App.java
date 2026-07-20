package com.mycompany.worms2therevenge;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
import com.mycompany.worms2therevenge.scenes.MainMenu;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        MainMenu menu = new MainMenu();
        
        menu.start(stage);
        
    }

    public static void main(String[] args) {
        launch();
    }

}