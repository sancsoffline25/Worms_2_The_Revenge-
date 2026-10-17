package com.mycompany.worms2therevenge;

import javafx.application.Application;
import javafx.stage.Stage;
import com.mycompany.worms2therevenge.scenes.MainMenu;
import entities.Player;

public class App extends Application{

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        Player jugador = new Player();

        MainMenu menu = new MainMenu();
        menu.start(stage, jugador);
    }

    public static void main(String[] args) {
        launch();
    }
}