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
import ui.ResolutionManager;
import sounds.uisounds;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

//Recurso
import ui.ButtonCreator;

public class OptionsMenu{
    
    Memoria memoria = new Memoria();
    uisounds sonidosInterfaz = new uisounds();
        
    Font textoFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 28);
    ButtonCreator buttonMaker = new ButtonCreator();
    

        
       public void start(Stage stage){
        
        Font tituloFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 44);
        Label titulo = new Label("   Opciones");
        titulo.setFont(tituloFont);
        titulo.setTextFill(Color.WHITE);
        
        //Botones
        StackPane botonVolver = buttonMaker.crearBoton("        Volver", textoFont); 
        
        //Aca le damos la función de volver
        botonVolver.setOnMouseClicked(e ->{
            memoria.desmarcarEscuchadaMusic();
            sonidosInterfaz.playSoundButton();
            MainMenu menu = new MainMenu();
            
            menu.start(stage);
        });
        
        //Aca el Layout vertical
        VBox layout = new VBox(20);
        Image fondo = new Image(
        getClass().getResource(
        "/Assets/Backgrounds/OptionMenu/OptionsMenuBackground.jpg").toExternalForm()
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
        
        layout.getChildren().addAll( 
                titulo,
                botonVolver
        );
        
        layout.setBackground(new Background(backgroundImage));
        layout.setAlignment(Pos.CENTER_LEFT); //ponemos el layout a la izquierda
        botonVolver.setAlignment(Pos.CENTER_LEFT);
        
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
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.setResizable(false);
        stage.show(); //Mostrar Escena
        
        
        
}
    
}
