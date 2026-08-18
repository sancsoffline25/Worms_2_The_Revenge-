/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Santiago Guinel
 */

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MainMenu {
    Font textoFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 28);
    
    private StackPane crearBoton(String texto, Font fuente) {

    Image botonImage = new Image(
        getClass().getResourceAsStream("/Assets/ui/botonBase.png.png")
    );
    
    Image botonHoverImage = new Image(
        getClass().getResourceAsStream("/Assets/ui/botonBaseHover.png")
    );
    
    Image botonPressedImage = new Image(
        getClass().getResourceAsStream("/Assets/ui/botonBasePressed.png")
    );

    ImageView botonView = new ImageView(botonImage);
    botonView.setFitWidth(256);
    botonView.setFitHeight(64);

    Label textoBoton = new Label(texto);
    textoBoton.setFont(fuente);
    textoBoton.setTextFill(Color.BLACK);

    StackPane boton = new StackPane(
        botonView,
        textoBoton
    ); //Función plantilla para crear botones

        //Mouse encima
        boton.setOnMouseEntered(e ->{
            botonView.setImage(botonHoverImage);
        });
        
        //Mouse fuera
        boton.setOnMouseExited(e ->{
            botonView.setImage(botonImage);
        });
        
        boton.setOnMousePressed(e ->{
            botonView.setImage(botonPressedImage);
        });
        
        boton.setOnMouseReleased(e ->{
            botonView.setImage(botonImage);
        });
    
    return boton;
    }
  
    public void start(Stage stage){
        
        //Titulo del juego
        Font tituloFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 44);
        Label titulo = new Label("Worms 2 The Revenge");
        titulo.setFont(tituloFont);
        titulo.setTextFill(Color.WHITE);
        
        //Botones
        StackPane playButton = crearBoton("Jugar", textoFont);
        StackPane minigamesButton = crearBoton("Minijuegos", textoFont);
        StackPane optionsButton = crearBoton("Opciones", textoFont);
        StackPane exitButton = crearBoton("Salir", textoFont);
        
        //Aca le asignamos el tamaño de los Botones
        playButton.setPrefWidth(200);
        minigamesButton.setPrefWidth(200);
        optionsButton.setPrefWidth(200);
        exitButton.setPrefWidth(200);
        
        //=== funciones de los botoncitos ===
        
        exitButton.setOnMouseClicked(e -> {
             stage.close();
        });


        minigamesButton.setOnMouseClicked(e -> {
            MiniGamesMenu menu = new MiniGamesMenu();
    
            menu.start(stage);
        });


        playButton.setOnMouseClicked(e -> {
            GamePlayBase menu = new GamePlayBase();
    
            menu.start(stage);
            
        });
        
        
        
        //Aca el Layout vertical(Las VBOX tambien las ocupaba en Godot, Me traen recuerdos)
        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: black;");
        
        layout.getChildren().addAll( 
                titulo,
                playButton,
                minigamesButton,
                optionsButton,
                exitButton
        ); //Aca el VBox layout se hace papá de los botones, asi los ordena
        
        layout.setAlignment(Pos.CENTER); //ponemos el layout al centro
        
        
        //Escena
        Scene escena = new Scene(layout, 1280, 720); //Parametros de la ventana
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.show(); //Mostrar Escena
        
              
    }
    
    
}
