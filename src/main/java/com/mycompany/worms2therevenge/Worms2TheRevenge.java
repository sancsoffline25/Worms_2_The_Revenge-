/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.worms2therevenge;

/**
 *
 * @author Santiago Guinel y Lautaro Gutierrez
 */

import com.mycompany.worms2therevenge.scenes.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Worms2TheRevenge extends Application{

    @Override
    public void start(Stage stage){

        MainMenu menu = new MainMenu();
        menu.start(stage);

    }

    public static void main(String[] args){
        launch(args);
    }
}


