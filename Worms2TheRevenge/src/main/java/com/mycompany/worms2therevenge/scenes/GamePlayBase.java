/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Santiago Guinel
 * Recomiendo escuchar "Chicago" de MJ mientras programas.
 * Concuerdo
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
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.media.AudioClip; 


//import entidades
import entities.Player;
import entities.Enemy;
import sounds.oldmansounds;

//import recursos (por asi decirle)
import ui.DialogueBlox;

public class GamePlayBase{
    
    // === Atributos de la clase ===
    
    //Variables Atributo de la clase
     Random worms = new Random();
     int manoCorrecta = worms.nextInt(2) + 1;
     
     Random sprites = new Random();
     int wormsprites = sprites.nextInt(3) + 1; //una herramienta secreta que nos ayudará mas tarde
     
     //StackPanes
     StackPane oldmanPane = new StackPane();
     StackPane leftHandPane = new StackPane();
     StackPane rightHandPane = new StackPane();
     
     //Incorporación del jugador
     Player jugador = new Player();
     
     //Incorporación del enemigo
     Enemy viejo = new Enemy(); //una herramienta secreta que nos ayudará mas tarde x2
     
     //Sonidos del enemigo
     oldmansounds viejoSonidos = new oldmansounds(); 
     
     
     //Botones
     Button lefthand = new Button(); 
     Button righthand = new Button();
    
    //Pausas
    PauseTransition handTimer = new PauseTransition(Duration.millis(500));
    
    //sprites manos
    Image leftimage = new Image(getClass().getResourceAsStream("/Assets/Sprites/manos/frame1.png") //cargamos las imagenes (manos cerradas)
    );
    
    Image rightimage = new Image(getClass().getResourceAsStream("/Assets/Sprites/manos/frame1.png")
    );
    
    Image leftimageopen = new Image(getClass().getResourceAsStream("/Assets/Sprites/manos/frame3.png") //cargamos las imagenes (manos abiertas)
    );
    
    Image rightimageopen = new Image(getClass().getResourceAsStream("/Assets/Sprites/manos/frame3.png")
    );
    
    
    //sprites gusanos
    Image worm1 = new Image(getClass().getResourceAsStream("/Assets/Sprites/worms/gusano1.png")); //aca van los gusanitos facheros
    Image worm2 = new Image(getClass().getResourceAsStream("/Assets/Sprites/worms/gusano2.png"));
    
    
    //ImageView Manos
    ImageView leftView = new ImageView(leftimage);
    ImageView rightView = new ImageView(rightimage); //para que se vean
    
    //ImageView gusanos
    ImageView wormView1 = new ImageView(worm1);
    ImageView wormView2 = new ImageView(worm2);
    
    //sprites enemigo
     Image enemieIdleSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/viejo/oldman.png"));
    
     //ImageView enemigo
     ImageView enemyIdle = new ImageView(enemieIdleSprite);
     
     //Dialogo del enemigo
     DialogueBlox dialogo = new DialogueBlox();

    //Metodos
    
    private void abrirManoDer(){
        handTimer.stop();
        
        //cerrar ambas manos
        leftView.setImage(leftimage);
        rightView.setImage(rightimage);
        
        //ocultar gusanos
        wormView1.setVisible(false);
        wormView2.setVisible(false);
        
        //abrir la mano derecha
        rightView.setImage(rightimageopen);
        
        handTimer.setOnFinished(e ->{
            wormView2.setVisible(false);
            rightView.setImage(rightimage);
        });
        
        handTimer.playFromStart();
    }
    
    private void abrirManoIzq(){
        handTimer.stop();
        
        //cerrar ambas manos
        leftView.setImage(leftimage);
        rightView.setImage(rightimage);
        
        //ocultar gusanos
        wormView1.setVisible(false);
        wormView2.setVisible(false);
        
        //abrir la mano izquierda
        leftView.setImage(leftimageopen);
        
        handTimer.setOnFinished(e ->{
            wormView1.setVisible(false);
            leftView.setImage(leftimage);
        });
        
        handTimer.playFromStart();
    }
    
    
    //Dialogo Inicial
    public void dialogoIntro(){ 
    
        //Dialogo1
        dialogo.mostrar("Viejo", "Hola pequeño... ¿Buscas a tus amigos?");
        viejoSonidos.playDialogue1();
        PauseTransition pausa1 = new PauseTransition(Duration.seconds(6));
        
        //desactivamos los botones
        righthand.setDisable(true);
        lefthand.setDisable(true);
        //y hacemos las manos invisibles
        righthand.setVisible(false);
        lefthand.setVisible(false);
        
        pausa1.setOnFinished (e -> {
            //Dialogo2
            dialogo.mostrar("Viejo", "Juguemos algo...¿si?");
            viejoSonidos.playDialogue2();
            
            PauseTransition pausa2 = new PauseTransition(Duration.seconds(5));
            
            pausa2.setOnFinished(e2 -> {
                //aca mostramos las manos
                righthand.setVisible(true);
                lefthand.setVisible(true);
                
               //Dialogo3
               dialogo.mostrar("Viejo", "Dime ¿en que mano tengo al gusano?");
               viejoSonidos.playDialogue3();
               
               PauseTransition pausa3 = new PauseTransition(Duration.seconds(5));
               
               pausa3.setOnFinished(e3 -> {
                //Dialogo4
                dialogo.mostrar("Viejo", "Si adivinas 20 veces, quizás te los devuelva.");
                viejoSonidos.playDialogue4();
                
                PauseTransition pausa4 = new PauseTransition(Duration.seconds(6));
                
                pausa4.setOnFinished(e4 -> {
                righthand.setDisable(false);
                lefthand.setDisable(false);
                dialogo.ocultar();
                }); 
                
                pausa4.play();
               });
               
               pausa3.play();
            });
            
            pausa2.play();
        });
        
       pausa1.play();
    }
    

    // === ACA ARRANCA LA ESCENA ===
    
    public void start(Stage stage){
        
    //=== Declaración de variables a usar ===    
    
    //Contenedores
    VBox root = new VBox(90);
    
    root.setStyle("-fx-background-color: black;");
    
    HBox botones = new HBox(240);
    
    HBox hud = new HBox(90);
    
    VBox dialogueContainer= new VBox(90);
    dialogueContainer.setTranslateY(-400);
    
    //Textos
    Label resultado = new Label(""); //Te muestra si acertaste o no
    Label intentosRestantes = new Label(""); //Te dice cuantos intentos tenes
    
    resultado.setFont(Font.font("Console", 25));
    resultado.setTextFill(Color.WHITE);
    
    resultado.setTranslateY(60);
    
    intentosRestantes.setFont(Font.font("Console", 20));
    intentosRestantes.setTextFill(Color.WHITE);
    
    
    //Acomodación de los sprites
    
    //-Gusanos
    wormView1.setFitWidth(60);
    wormView1.setFitHeight(60);
    
    wormView2.setFitWidth(60);
    wormView2.setFitHeight(60);
    
    wormView1.setVisible(false);
    wormView2.setVisible(false);
    
    wormView1.setTranslateY(-25);
    wormView2.setTranslateY(-25);
    
    //-Manos
    leftView.setFitWidth(160);
    leftView.setFitHeight(160);

    rightView.setFitWidth(160);
    rightView.setFitHeight(160); //tamaño de los sprites (altura y anchura)
    
    lefthand.setGraphic(leftView); //para poner las imagenes dentro de los botones
    righthand.setGraphic(rightView);
    
    righthand.setScaleX(-1);
    
    lefthand.setTranslateY(-15);
    righthand.setTranslateY(-15);
    
    lefthand.setStyle("-fx-background-color: transparent;");
    righthand.setStyle("-fx-background-color: transparent;");
    
    //-Enemigo
    enemyIdle.setFitWidth(700);
    enemyIdle.setFitHeight(700);
    
    enemyIdle.setTranslateY(470
    
    );
    enemyIdle.setTranslateX(-50);
    
    //Organización de los Botones, Labels y StackPane
    
    root.getChildren().addAll(
            oldmanPane,
            dialogueContainer,
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
    
    leftHandPane.getChildren().addAll(
            leftView,
            wormView1
    );
    
    rightHandPane.getChildren().addAll(
            rightView,
            wormView2
    );
    
    oldmanPane.getChildren().addAll(
            enemyIdle,
            leftHandPane,
            rightHandPane
    );
    
        
    dialogueContainer.getChildren().add(dialogo);

    //SetGraphic Manos
    lefthand.setGraphic(leftHandPane);
    righthand.setGraphic(rightHandPane);
    
    
    //Posicionamiento de los contenedores
    
    botones.setAlignment(Pos.BOTTOM_CENTER);
    
    root.setAlignment(Pos.BOTTOM_CENTER);
    
    hud.setAlignment(Pos.BOTTOM_CENTER);
    
    dialogueContainer.setAlignment(Pos.TOP_CENTER);
    
    //=== Lógica del Gameplay ===
    
    intentosRestantes.setText("Intentos: "+ jugador.getReintentos());
    
    lefthand.setOnAction(e -> {
        
        abrirManoIzq();
        
       if(manoCorrecta == 1){
           resultado.setText("Acertaste!");
           wormView1.setVisible(true);
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
        
        abrirManoDer();
        
        if(manoCorrecta == 2){
           resultado.setText("Acertaste!");
           wormView2.setVisible(true);
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
    wormView1.setScaleX(1.2);
    wormView2.setScaleY(1.2);
    });

    lefthand.setOnMouseExited(e -> {
    leftView.setScaleX(1);
    leftView.setScaleY(1);
    wormView1.setScaleX(1);
    wormView1.setScaleY(1);
    });
    
    righthand.setOnMouseEntered(e -> {
       rightView.setScaleX(1.2);
       rightView.setScaleY(1.2);
       wormView2.setScaleX(1.2);
       wormView2.setScaleY(1.2);
    });
    
    righthand.setOnMouseExited(e -> {
    rightView.setScaleX(1);
    rightView.setScaleY(1);
    wormView2.setScaleX(1);
    wormView2.setScaleY(1);
    });
    
    
    
    
    //Escena
       Scene escena = new Scene(root, 1280, 720); //Parametros de la ventana
        
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.show(); //Mostrar Escena 
        dialogoIntro();
}
    
}
