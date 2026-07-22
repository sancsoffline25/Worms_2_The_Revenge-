/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Santiago Guinel
 */
import java.util.Random; //Separado al ser de Java y no JavaFX

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//import entidades
import entities.Player;

public class GamePlayBase{
    
    //Variables Atributo de la clase
     Random worms = new Random();
     int manoCorrecta = worms.nextInt(2) + 1;
     
     //Incorporación del jugador
     Player jugador = new Player();
    
    public void start(Stage stage){
        
    //=== Declaración de variables a usar ===    
    
    Button lefthand = new Button();
    Button righthand = new Button();
    
    
            
    VBox root = new VBox(90);
    
    HBox botones = new HBox(30);
    
    HBox hud = new HBox(90);
    
    Label resultado = new Label("");
    Label intentosRestantes = new Label("");
    
    //sprites manos
    Image leftimage = new Image(getClass().getResourceAsStream("/Assets/Sprites/manos/frame1.png") //cargamos las imagenes
    );
    
    Image rightimage = new Image(getClass().getResourceAsStream("/Assets/Sprites/manos/frame1.png")
    );
    
    Image leftimageopen = new Image(getClass().getResourceAsStream("/Assets/Sprites/manos/frame3.png") //cargamos las imagenes
    );
    
    Image rightimageopen = new Image(getClass().getResourceAsStream("/Assets/Sprites/manos/frame3.png")
    );
    
    ImageView leftView = new ImageView(leftimage);
    ImageView rightView = new ImageView(rightimage); //para que se vean
    
    leftView.setFitWidth(180);
    leftView.setFitHeight(180);

    rightView.setFitWidth(180);
    rightView.setFitHeight(180); //tamaño de los sprites (altura y anchura)
    
    lefthand.setGraphic(leftView);
    righthand.setGraphic(rightView); 
    
    righthand.setScaleX(-1);
    lefthand.setStyle("-fx-background-color: transparent;");
    righthand.setStyle("-fx-background-color: transparent;");
    
    //Organización de los Botones y Labels
    
    root.getChildren().addAll(
            resultado,
            botones,
            hud
    );
    
    botones.getChildren().addAll(
            lefthand,
            righthand
    );
    
    hud.getChildren().add(
            intentosRestantes
    );
    
    //Posicionamiento de los contenedores
    
    botones.setAlignment(Pos.BOTTOM_CENTER);
    
    root.setAlignment(Pos.BOTTOM_CENTER);
    
    hud.setAlignment(Pos.CENTER_RIGHT);
    
    //=== Lógica del Gameplay ===
    
    intentosRestantes.setText("Intentos: "+ jugador.getReintentos());
    
    lefthand.setOnAction(e -> {
       if(manoCorrecta == 1){
           resultado.setText("Acertaste!");
       }else{
           resultado.setText("Respuesta incorrecta");
           jugador.perderIntento();
           intentosRestantes.setText("Intentos: "+ jugador.getReintentos());
       }
       
       
       if(jugador.sinIntentos()){
        lefthand.setVisible(false);
        righthand.setVisible(false);
        resultado.setText("Game Over");
        }
       
       manoCorrecta = worms.nextInt(2) + 1;
    });
    
    righthand.setOnAction(e -> {
        
        
        if(manoCorrecta == 2){
           resultado.setText("Acertaste!");
       }else{
           resultado.setText("Respuesta incorrecta");
           jugador.perderIntento();
           intentosRestantes.setText("Intentos: "+ jugador.getReintentos());
       }

        
        if(jugador.sinIntentos()){
        lefthand.setVisible(false);
        righthand.setVisible(false);
        resultado.setText("Game Over");
        return;
        }
        
        manoCorrecta = worms.nextInt(2) + 1;
    });
    
    
    //Efectos visuales
    lefthand.setOnMouseEntered(e -> {
    leftView.setScaleX(1.2);
    leftView.setScaleY(1.2);
    });

    lefthand.setOnMouseExited(e -> {
    leftView.setScaleX(1);
    leftView.setScaleY(1);
    });
    
    righthand.setOnMouseEntered(e -> {
       rightView.setScaleX(1.2);
       rightView.setScaleY(1.2);
    });
    
    righthand.setOnMouseExited(e -> {
    rightView.setScaleX(1);
    rightView.setScaleY(1);
    });
    
    
    
    
    //Escena
       Scene escena = new Scene(root, 800, 600); //Parametros de la ventana
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.show(); //Mostrar Escena 
    
}
    
}
