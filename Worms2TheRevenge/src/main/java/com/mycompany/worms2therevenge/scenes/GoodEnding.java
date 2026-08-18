/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Santiago Guinel
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GoodEnding {
    
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
        //Contenedores
        VBox layout = new VBox(40);
        VBox creditos = new VBox(20);
        
        //fondo
        layout.setStyle("-fx-background-image: url('/Assets/Backgrounds/GoodEnding/GoodEndingFinale.png');" +
    "-fx-background-size: cover;" +
    "-fx-background-position: center center;" +
    "-fx-background-repeat: no-repeat;");
        
        //Label (carteles)
        Label finaleText = new Label("GOOD ENDING");
        Label programmers = new Label("Programado por: Santiago Guinel y Lautaro Gutierrez");
        Label art = new Label("Arte realizado por: Viggo Sanchez");
        Label curso = new Label("Curso: 6to 6ta - Programación"); 
        Label musicUsed = new Label("Musica utilizada LEASE by Takeshi Abo");
        
        //Botones
        StackPane backToMenu = crearBoton("Volver al menú", textoFont);
        
        //Textos(propiedades)
        Font textoFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 28);
        Font textoPrincipalFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 52);
        finaleText.setFont(textoPrincipalFont);
        programmers.setFont(textoFont);
        art.setFont(textoFont);
        curso.setFont(textoFont);
        musicUsed.setFont(textoFont);
        
        //Aca hacemos el fade in del inicio
        Rectangle fade = new Rectangle(1280, 720, Color.WHITE);
        fade.setMouseTransparent(true); //con esto el fade ignora al mouse para que no bloquee los botones
        StackPane escenaFinal = new StackPane();
        
        escenaFinal.getChildren().addAll(
                layout,
                fade
        );
        
        FadeTransition fadeIn = new FadeTransition(
                Duration.seconds(3),
                fade
        );
        
        fadeIn.setFromValue(1);
        fadeIn.setToValue(0);
        
        fadeIn.play();
        
       
        //Efecto bonito para los textos (sombra)
        finaleText.setStyle(
        "-fx-text-fill: white;" +
        "-fx-effect: dropshadow(gaussian, black, 0, 0, 3, 3);"
        );
        
        programmers.setStyle(
        "-fx-text-fill: yellow;" +
        "-fx-effect: dropshadow(gaussian, black, 0, 0, 3, 3);"
        );
        
        art.setStyle(
        "-fx-text-fill: yellow;" +
        "-fx-effect: dropshadow(gaussian, black, 0, 0, 3, 3);"
        );
        
        curso.setStyle(
        "-fx-text-fill: yellow;" +
        "-fx-effect: dropshadow(gaussian, black, 0, 0, 3, 3);"
        );
        
        musicUsed.setStyle(
        "-fx-text-fill: yellow;" +
        "-fx-effect: dropshadow(gaussian, black, 0, 0, 3, 3);"
        );
        
        
        //Hacemos papá a los creditos
        creditos.getChildren().addAll(
                programmers,
                art,
                curso,
                musicUsed
        );
        
        //Hacemos abuelo al layout
        layout.getChildren().addAll(
                finaleText,
                creditos,
                backToMenu
        );
        
        //Ordén del contenedor
        layout.setAlignment(Pos.CENTER);
        creditos.setAlignment(Pos.CENTER);
        
        
        //Escena
        Scene escena = new Scene(escenaFinal, 1280, 720);
        
        //Musica
        Media musica = new Media(getClass().getResource("/Assets/Musica/LEASE_GoodEnding.mp3").toExternalForm()); //cargo la musica
        MediaPlayer reproductor = new MediaPlayer(musica); //creo el reproductor que va a reproducirla
        reproductor.setVolume(0.3); //volumen tranqui
        reproductor.setCycleCount(MediaPlayer.INDEFINITE); //hago que este en loop
        reproductor.play(); //arranca el temón
        
        //Lógica botón para volver
        backToMenu.setOnMouseClicked(e ->{
            MainMenu menu = new MainMenu();
            reproductor.stop(); 
            
            menu.start(stage);
        });
        
        stage.setTitle("Worms 2: The Revenge");
        stage.setScene(escena);
        stage.show();
        
    }
    
}
