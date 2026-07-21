/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Lautaro "LaroStar" Gutierrez
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class MiniPesca {
    
    public void start(Stage stage){
        
        Label titulo = new Label("Aca va el minijuego");
        
         //Botones 
        Button backButton = new Button("Volver"); 
        
        //Aca le asignamos el tamaño de los Botones
        backButton.setPrefWidth(160);
        
        //Aca le damos la función de volver(backButton)
        backButton.setOnAction(e -> {
            MiniGamesMenu menu = new MiniGamesMenu();
            
            menu.start(stage);
        });
      
        VBox layout = new VBox(20);
        layout. setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                titulo,
                backButton
        );
        
        Scene escena = new Scene(layout,800,600);
        
        stage.setScene(escena);
       
    }
    
}
