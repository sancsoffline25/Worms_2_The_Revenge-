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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;




public class MiniGamesMenu {
     
        
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
        Label titulo = new Label("Minijuegos");
        titulo.setFont(tituloFont);
        titulo.setTextFill(Color.WHITE);
        
        //Botones
        StackPane minigame1Button = crearBoton("MiniPesca", textoFont);
        StackPane minigame2Button = crearBoton("MiniBúsqueda", textoFont);
        StackPane minigame3Button = crearBoton("MiniAtrapada", textoFont);
        StackPane backButton = crearBoton("Volver", textoFont);

        //Aca le damos la función de volver(backButton)
        backButton.setOnMouseClicked(e -> {
            MainMenu menu = new MainMenu();
            
            menu.start(stage);
        });
        
        minigame1Button.setOnMouseClicked(e -> {
           MiniPesca menu = new MiniPesca();
           
           menu.start(stage);
        });
        
        //Aca el Layout vertical
        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: black;");
        
        layout.getChildren().addAll( 
                titulo,
                minigame1Button,
                minigame2Button,
                minigame3Button,
                backButton
        ); //Aca el VBox layout se hace papá de los botones, asi los ordena
        
        layout.setAlignment(Pos.CENTER); //ponemos el layout al centro
        
        
        //Escena
        Scene escena = new Scene(layout, 1280, 720); //Parametros de la ventana
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.show(); //Mostrar Escena
        
        
        
}
    
    
}


    
