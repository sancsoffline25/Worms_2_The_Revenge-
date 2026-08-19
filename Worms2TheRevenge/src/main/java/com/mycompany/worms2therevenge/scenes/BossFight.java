/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;


import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//Entidades
import entities.Enemy;
import entities.Player;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Santiago Guinel
 */
public class BossFight{
    
    //=== Atributos de la clase ===
    
    //Traemos a las entidades protagonistas
    Player jugador = new Player();
    Enemy viejo = new Enemy();
    
    //Sprites de las entidades
    ImageView playerView = new ImageView(jugador.getIdleSprite());
    ImageView viejoView = new ImageView(viejo.getIdleSprite());
    
    //BattleBox
    Rectangle battleBox= new Rectangle(800, 400);

    public void start(Stage stage){
        
        //Personalización de la battleBox
        battleBox.setFill(Color.BLACK);
        battleBox.setStroke(Color.WHITE);
        battleBox.setStrokeWidth(5);
        
        //Contenedor principal
        StackPane escenaFinal = new StackPane();
        escenaFinal.getChildren().addAll(
                battleBox,
                playerView
        );
        
        escenaFinal.setStyle("-fx-background-color: black");
        
        //Escena
        Scene escena = new Scene(escenaFinal);
        
        escena.setOnKeyPressed(e->{
            switch(e.getCode()){
                
                case W:
                jugador.mover(0, -10);
                break;
                
                case S:
                jugador.mover(0, 10);
                break;
            
                case A:
                jugador.mover (-10, 0);
                break;
                
                case D:
                jugador.mover(10,0);
                break;
            }
            playerView.setTranslateX(jugador.getX());
            playerView.setTranslateY(jugador.getY());
        });
        
        stage.setTitle("Worms 2: The Revenge");
        stage.setScene(escena);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();
    }
    
}
