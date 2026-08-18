/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Santiago Guinel
 */
public class BossFight{
    
    public void start(Stage stage){
        //Contenedor principal
        StackPane escenaFinal = new StackPane();
        
        
        //Escena
        Scene escena = new Scene(escenaFinal);
        
        stage.setTitle("Worms 2: The Revenge");
        stage.setScene(escena);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();
    }
    
}
