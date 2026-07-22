/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Lautaro Gutierrez
 */
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;




public class MiniGamesMenu {
     
        
       public void start(Stage stage){
        
        //Titulo del juego
        Label titulo = new Label("Minijuegos");
        titulo.setFont(new Font(40));
        
        //Botones 
        Button minigame1Button = new Button("MiniPesca"); 
        Button minigame2Button = new Button("MiniBúsqueda");
        Button minigame3Button = new Button("MiniAtrapada");
        Button backButton = new Button("Volver"); 
        
        //Aca le asignamos el tamaño de los Botones
        minigame1Button.setPrefWidth(160);
        minigame2Button.setPrefWidth(160);
        minigame3Button.setPrefWidth(160);
        backButton.setPrefWidth(160);
        
        //Aca le damos la función de volver(backButton)
        backButton.setOnAction(e -> {
            MainMenu menu = new MainMenu();
            
            menu.start(stage);
        });
        
        minigame1Button.setOnAction(e -> {
           MiniPesca menu = new MiniPesca();
           
           menu.start(stage);
        });
        
        //Aca el Layout vertical
        VBox layout = new VBox(20);
        
        layout.getChildren().addAll( 
                titulo,
                minigame1Button,
                minigame2Button,
                minigame3Button,
                backButton
        ); //Aca el VBox layout se hace papá de los botones, asi los ordena
        
        layout.setAlignment(Pos.CENTER); //ponemos el layout al centro
        
        
        //Escena
        Scene escena = new Scene(layout, 800, 600); //Parametros de la ventana
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.show(); //Mostrar Escena
        
        
        
}
    
    
}


    
