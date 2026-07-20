/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Santiago Guinel
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class MainMenu {
    
  
    public void start(Stage stage){
        
        Label testtex = new Label("Worms 2 The Revenge");
        
        Scene escena = new Scene(testtex, 800, 600);
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.show();
        
}
    
    
}
