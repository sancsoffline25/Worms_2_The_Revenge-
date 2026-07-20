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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class MainMenu {
    
  
    public void start(Stage stage){
        
        //Titulo del juego
        Label titulo = new Label("Worms 2 The Revenge");
        titulo.setFont(new Font(40));
        
        //Botones
        Button playButton = new Button("Jugar"); //Aca creamos los botones del Menú
        Button minigamesButton = new Button("Minijuegos");
        Button optionsButton = new Button("Opciones");
        Button exitButton = new Button("Salir");
        
        //Aca le asignamos el tamaño de los Botones
        playButton.setPrefWidth(200);
        minigamesButton.setPrefWidth(200);
        optionsButton.setPrefWidth(200);
        exitButton.setPrefWidth(200);
        
        //Aca le damos la función de salir al botón de sálir (una obviedad lo se)
        exitButton.setOnAction(e -> {
            stage.close();
        });
        
        //Aca el Layout vertical(Las VBOX tambien las ocupaba en Godot, Me traen recuerdos)
        VBox layout = new VBox(20);
        
        layout.getChildren().addAll( 
                titulo,
                playButton,
                minigamesButton,
                optionsButton,
                exitButton
        ); //Aca el VBox layout se hace papá de los botones, asi los ordena
        
        layout.setAlignment(Pos.CENTER); //ponemos el layout al centro
        
        
        //Escena
        Scene escena = new Scene(layout, 800, 600); //Ventana de la escena
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.show(); //Mostrar Escena
        
        
        
}
    
    
}
