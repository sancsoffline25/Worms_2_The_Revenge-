/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Santiago Guinel
 */

import entities.Memoria;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ui.ResolutionManager;
import sounds.uisounds; 
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MainMenu {
    
    //musica
    Media musica = new Media(getClass().getResource("/Assets/Musica/extendMainMenuSong.mp3").toExternalForm()); //cargo la musica
    MediaPlayer reproductor = new MediaPlayer(musica); //creo el reproductor que va a reproducirla
    Memoria memoria = new Memoria();
    
    private void reproducirMusica(){
        reproductor.setVolume(0.3); //volumen tranqui
        reproductor.setCycleCount(MediaPlayer.INDEFINITE); //hago que este en loop
        reproductor.play(); //arranca el temónc
        memoria.marcarEscuchadaMusic(); 
    }
    
    public void detenerMusica(){
        reproductor.stop();
    }
    
    //booleano pa la musica
    
        
    
    //Sonidos de la interfaz
    uisounds sonidosInterfaz = new uisounds();
    
    //fuente y botones
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
            sonidosInterfaz.playButtonFocus();
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
        /*Font tituloFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 44);
        Label titulo = new Label("");
        titulo.setFont(tituloFont);
        titulo.setTextFill(Color.WHITE);*/
        
        Image titulosprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/logomenu/worms2logo.png"));
        ImageView titulo = new ImageView(titulosprite); 
        
        titulo.setFitWidth(480); 
        titulo.setFitHeight(320);
        
        //Botones
        StackPane playButton = crearBoton("Jugar", textoFont);
        StackPane optionsButton = crearBoton("Opciones", textoFont);
        StackPane exitButton = crearBoton("Salir", textoFont);
        
        //Aca le asignamos el tamaño de los Botones
        playButton.setPrefWidth(200);
        optionsButton.setPrefWidth(200);
        exitButton.setPrefWidth(200);
        
        //=== funciones de los botoncitos ===
        
        exitButton.setOnMouseClicked(e ->{
            sonidosInterfaz.playSoundButton();
             stage.close();
        });

        playButton.setOnMouseClicked(e ->{
            memoria.desmarcarEscuchadaMusic();
            detenerMusica();
            sonidosInterfaz.playSoundButton();
            GamePlayBase menu = new GamePlayBase();
    
            menu.start(stage);
            
        });
        
        optionsButton.setOnMouseClicked(e ->{
            sonidosInterfaz.playSoundButton();
            OptionsMenu menu = new OptionsMenu();
            menu.start(stage);
        });
        
        
        
        //Aca el Layout vertical(Las VBOX tambien las ocupaba en Godot, Me traen recuerdos)
        VBox layout = new VBox(20);
        StackPane logo = new StackPane();
        
        Image fondo = new Image(
        getClass().getResource(
        "/Assets/Backgrounds/MainMenu/MainMenuBackground2.jpg").toExternalForm()
        );

         BackgroundImage backgroundImage = new BackgroundImage(
        fondo,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,
        new BackgroundSize(
        1.0, 1.0,
        true, true,
        false, false
        ));
         
        layout.setBackground(new Background(backgroundImage));
        
        logo.getChildren().add(titulo);
        
        layout.getChildren().addAll( 
                logo,
                playButton,
                optionsButton,
                exitButton
        ); //Aca el VBox layout se hace papá de los botones, asi los ordena
        
        logo.setAlignment(Pos.TOP_CENTER);
        layout.setAlignment(Pos.CENTER); //ponemos el layout al centro
        
        
        // Contenedor de resolución base
        StackPane escenaFinal = new StackPane();

        escenaFinal.setPrefSize(
        ResolutionManager.BASE_WIDTH,
        ResolutionManager.BASE_HEIGHT
        );

        escenaFinal.setMinSize(
        ResolutionManager.BASE_WIDTH,
        ResolutionManager.BASE_HEIGHT
        );

        escenaFinal.setMaxSize(
        ResolutionManager.BASE_WIDTH,
        ResolutionManager.BASE_HEIGHT
        );

        escenaFinal.getChildren().add(layout);
        
        
        //Escena
        Scene escena = ResolutionManager.crearEscena(escenaFinal); //Parametros de la ventana
        
       if(!memoria.isMusicEscuchada()){
        reproducirMusica();
        }
        
        escena.setOnKeyPressed(e -> {

        if(e.getCode() == KeyCode.L){ //esto para vos lauty
        
        memoria.desmarcarEscuchadaMusic();
        detenerMusica();
        MiniGamesMenu menu = new MiniGamesMenu();
        menu.start(stage);
             }else if(e.getCode() == KeyCode.O){ //atajo bossfight
                memoria.desmarcarEscuchadaMusic();
                detenerMusica();
                BossFight menu = new BossFight();
                menu.start(stage);
             }else if(e.getCode() == KeyCode.P){
                 memoria.desmarcarEscuchadaMusic();
                 detenerMusica();
                 EpicEnding menu = new EpicEnding();
                 menu.start(stage);
             }
        });
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.setResizable(false);
        stage.show(); //Mostrar Escena
    
    }
    
    
}
