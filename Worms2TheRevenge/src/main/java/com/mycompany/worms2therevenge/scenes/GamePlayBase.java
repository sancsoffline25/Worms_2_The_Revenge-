/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Santiago Guinel
 * Recomiendo escuchar "Chicago" de MJ mientras programas.
 * 
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
import javafx.animation.FadeTransition;
import javafx.scene.control.OverrunStyle;

//import entidades
import entities.Player;
import entities.Enemy;

//import recursos (por asi decirle)
import ui.DialogueBlox;
import sounds.oldmansounds;
import animations.ScreenTransitions;
import javafx.scene.input.KeyCode;
import ui.ButtonCreator;

public class GamePlayBase{
    
 // === Atributos de la clase ===
    
    //Variables Atributo de la clase
     Random worms = new Random();
     int manoCorrecta = worms.nextInt(2) + 1;
     
     Random sprites = new Random();
     
     //StackPanes
     StackPane oldmanPane = new StackPane();
     StackPane leftHandPane = new StackPane();
     StackPane rightHandPane = new StackPane();
     
     //Incorporación del jugador
     Player jugador = new Player();
     
     //gusanos encontrados por el jugador
    int gusanosEncontrados = 0;
     
     
     //Incorporación del enemigo
     Enemy viejo = new Enemy(); //una herramienta secreta que nos ayudará mas tarde x2
     
     //Sonidos del enemigo
     oldmansounds viejoSonidos = new oldmansounds(); 
     
     //Transiciones bonitas
     ScreenTransitions transitions = new ScreenTransitions();
     
     //Botones
     Button lefthand = new Button(); 
     Button righthand = new Button();
     
     //Botones UI
     Font botonFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 28);
     ButtonCreator botonmaker = new ButtonCreator();
     StackPane botonVolver = botonmaker.crearBoton("Volver", botonFont);
     StackPane botonMiniPesca = botonmaker.crearBoton("MiniPesca", botonFont);
     
     
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
    Image worm3 = new Image(getClass().getResourceAsStream("/Assets/Sprites/worms/gusano3.png"));
    
    
    //ImageView Manos
    ImageView leftView = new ImageView(leftimage);
    ImageView rightView = new ImageView(rightimage); //para que se vean
    
    //ImageView gusanos
    ImageView wormViewLeft = new ImageView();
    ImageView wormViewRight = new ImageView();

    //sprites enemigo
     Image enemieIdleSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/viejo/oldman.png"));
     Image enemieTalkSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/viejo/oldmanTalk.png"));
     Image enemieEvilTalkSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/viejo/oldmanEvilTalk.png")); 
    
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
        wormViewRight.setVisible(false);
        wormViewLeft.setVisible(false);
        
        //abrir la mano derecha
        rightView.setImage(rightimageopen);
        
        handTimer.setOnFinished(e ->{
            wormViewRight.setVisible(false);
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
        wormViewLeft.setVisible(false);
        wormViewRight.setVisible(false);
        
        //abrir la mano izquierda
        leftView.setImage(leftimageopen);
        
        handTimer.setOnFinished(e ->{
            wormViewLeft.setVisible(false);
            leftView.setImage(leftimage);
        });
        
        handTimer.playFromStart();
    }
    
    private Image elegirGusano(){
        
        int wormsprites = sprites.nextInt(3) + 1;
        
        if(wormsprites == 1){
            return worm1; 
        }else if(wormsprites== 2){
            return worm2;
        }else{
            return worm3; 
        }
    }
    
    
    //Dialogo Inicial
    public void dialogoIntro(){ 
         //Viejo hablando sprite
         enemyIdle.setImage(enemieTalkSprite); 
         
        //Dialogo1
        dialogo.mostrar("Viejo", "Hola pequeño... ¿Buscas a tus amigos?");
        viejoSonidos.playDialogue1();
        PauseTransition pausa1 = new PauseTransition(Duration.seconds(6));
        
        //desactivamos los botones
        righthand.setDisable(true);
        lefthand.setDisable(true);
        
        //botones UI tambien
        botonMiniPesca.setVisible(false);
        
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
                botonMiniPesca.setVisible(true);
                dialogo.ocultar();
                enemyIdle.setImage(enemieIdleSprite); 
                }); 
                
                pausa4.play();
               });
               
               pausa3.play();
            });
            
            pausa2.play();
        });
        
       pausa1.play();
    }
    
    public void dialogoGameOver(Runnable alTerminar){
        
        PauseTransition pausaGO = new PauseTransition(Duration.seconds(2));
        
        pausaGO.setOnFinished(e ->{
            //Cambio sprite hablando
            enemyIdle.setImage(enemieEvilTalkSprite);
            
            //Dialogo perdiste
            dialogo.mostrar("Viejo", "Pequeño...creo que no entendiste la metáfora");
            viejoSonidos.playDialogueGO();
        
            PauseTransition pausaGO2 = new PauseTransition(Duration.seconds(8));
        
        pausaGO2.setOnFinished(e2 ->{
            dialogo.ocultar();
            enemyIdle.setImage(enemieIdleSprite); 
            
            alTerminar.run();
        });
        
        pausaGO2.play();
        });
        pausaGO.play();
    }
   
    public void esperaBossFight(){
        PauseTransition pausaBF = new PauseTransition(Duration.seconds(8));
        
        pausaBF.setOnFinished(e ->{
            //finaliza la pausa
        });
        pausaBF.play();
    }

    // === ACA ARRANCA LA ESCENA ===
    
    public void start(Stage stage){
        
    //=== Declaración de variables a usar ===    
    
    
    //StackPane contenedor padre
    StackPane escenaFinal = new StackPane();
    
    //Contenedores
    VBox root = new VBox(90);
     escenaFinal.getChildren().add(root);
    
    root.setStyle("-fx-background-image: url('/Assets/Backgrounds/GamePlayBase/fondoTest.jpg');" +
    "-fx-background-size: cover;" +
    "-fx-background-position: center center;" +
    "-fx-background-repeat: no-repeat;"    
    ); //CSS para que el fondo quede bien
    
    HBox botones = new HBox(240); //manos
    
    HBox hud = new HBox(90);
    
    VBox dialogueContainer= new VBox(90);
    dialogueContainer.setTranslateY(-250);
    
    VBox buttonContainer = new VBox(10); //botones literales
    
    //Textos
    Font textoFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 44);
    
    Label resultado = new Label(""); //Te muestra si acertaste o no
    Label intentosRestantes = new Label(""); //Te dice cuantos intentos tenes
    Label wormsFounded = new Label(""); //cartelito para los gusanos encontrados
    
    
    resultado.setFont(textoFont);
    resultado.setTextFill(Color.WHITE);
    resultado.setTranslateY(60);
    
    intentosRestantes.setFont(textoFont);
    intentosRestantes.setTextFill(Color.WHITE);
    
    wormsFounded.setFont(textoFont);
    wormsFounded.setTextFill(Color.WHITE); 
    
    
    //Acomodación de los sprites
    
    //-Gusanos
    wormViewLeft.setFitWidth(60);
    wormViewLeft.setFitHeight(60);
    wormViewLeft.setVisible(false);
    wormViewLeft.setTranslateY(-25);
    
    wormViewRight.setFitWidth(60);
    wormViewRight.setFitHeight(60);
    wormViewRight.setVisible(false);
    wormViewRight.setTranslateY(-25);
    
    //-Manos
    leftView.setFitWidth(160);
    leftView.setFitHeight(160);

    rightView.setFitWidth(160);
    rightView.setFitHeight(160); //tamaño de los sprites (altura y anchura)
    
    lefthand.setGraphic(leftView); //para poner las imagenes dentro de los botones
    righthand.setGraphic(rightView);
    
    righthand.setScaleX(-1);
    
    lefthand.setTranslateY(25);
    righthand.setTranslateY(25);
    
    lefthand.setStyle("-fx-background-color: transparent;");
    righthand.setStyle("-fx-background-color: transparent;");
    
    //-Enemigo
    enemyIdle.setFitWidth(700);
    enemyIdle.setFitHeight(700);
    
    enemyIdle.setTranslateY(600);
    enemyIdle.setTranslateX(-50);
    
    //Organización de los Botones, Labels y StackPane
    
    root.getChildren().addAll(
            oldmanPane,
            dialogueContainer,
            resultado,
            botones,
            hud
    );
    
    buttonContainer.getChildren().addAll(
            botonMiniPesca,
            botonVolver
    );
    
    botones.getChildren().addAll(
            lefthand,
            righthand
    );
    
    hud.getChildren().addAll(
            intentosRestantes,
            wormsFounded,
            buttonContainer
    );
    
    leftHandPane.getChildren().addAll(
            leftView,
            wormViewLeft
    );
    
    rightHandPane.getChildren().addAll(
            rightView,
            wormViewRight
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
    
    escenaFinal.setAlignment(Pos.BOTTOM_CENTER);
    
    dialogueContainer.setAlignment(Pos.TOP_CENTER);
    
    //=== Lógica del Gameplay ===
    
    intentosRestantes.setText("Intentos: "+ jugador.getReintentos());
    wormsFounded.setText("Gusanos " + gusanosEncontrados + "/20");
    
    //función Botones UI
    botonVolver.setOnMouseClicked(e ->{
        MainMenu menu = new MainMenu();
        
        menu.start(stage); 
    });
    
    botonMiniPesca.setOnMouseClicked(e ->{
        MiniPesca menu = new MiniPesca();
        
        menu.start(stage);
    });
    
    
    lefthand.setOnAction(e -> {
        
        abrirManoIzq();
        
       if(manoCorrecta == 1){
           resultado.setText("Acertaste!");
           
           gusanosEncontrados++;
           wormsFounded.setText("Gusanos " + gusanosEncontrados + "/20");
           
           wormViewLeft.setImage(elegirGusano());
           wormViewLeft.setVisible(true);
       }else{
           resultado.setText("Respuesta incorrecta");
           jugador.perderIntento();
           intentosRestantes.setText("Intentos: "+ jugador.getReintentos());
       }
       
       
       if(jugador.sinIntentos()){
        lefthand.setVisible(false);
        righthand.setVisible(false);
        resultado.setText("Game Over");
        
        dialogoGameOver(() ->{
        BossFight menu = new BossFight();
        menu.start(stage);
        });
        
        return;
        }
       
         
          // --- GOOD ENDING ---
        if(gusanosEncontrados >= 20){
        resultado.setText("Ganaste"); //le decimos que gano
        
        //y le ocultamos las manos
        lefthand.setDisable(true); 
        righthand.setDisable(true);
        lefthand.setVisible(false);
        righthand.setVisible(false);
        //Cambiamos de escena
       FadeTransition transicion = transitions.fadeOutWhite(escenaFinal, 2);

        transicion.setOnFinished(e2 -> {
            GoodEnding menu = new GoodEnding();
            menu.start(stage);
        });

        return;
    }
       
       manoCorrecta = worms.nextInt(2) + 1;
    });
    
    righthand.setOnAction(e -> {
        
        abrirManoDer();
        
        if(manoCorrecta == 2){
           resultado.setText("Acertaste!"); //Pone el texto
           
           gusanosEncontrados++;
           wormsFounded.setText("Gusanos " + gusanosEncontrados + "/20"); //suma los gusanos

           wormViewRight.setImage(elegirGusano()); //muestra la mano y el gusano
           wormViewRight.setVisible(true);
       }else{
           resultado.setText("Respuesta incorrecta");
           jugador.perderIntento();
           intentosRestantes.setText("Intentos: "+ jugador.getReintentos());
       }

        
        if(jugador.sinIntentos()){
        lefthand.setVisible(false);
        righthand.setVisible(false);
        resultado.setText("Game Over");
        
        dialogoGameOver(() ->{
        BossFight menu = new BossFight();
        menu.start(stage);
        });
        
        return;
        }
        
          // --- GOOD ENDING ---
        if(gusanosEncontrados >= 20){
        resultado.setText("Ganaste"); //le decimos que gano
        
        //y le ocultamos las manos
        lefthand.setDisable(true); 
        righthand.setDisable(true);
        lefthand.setVisible(false);
        righthand.setVisible(false);
        //Cambiamos de escena
       FadeTransition transicion = transitions.fadeOutWhite(escenaFinal, 2);

        transicion.setOnFinished(e2 -> {
            GoodEnding menu = new GoodEnding();
            menu.start(stage);
        });

        return;
    }
    
        
        manoCorrecta = worms.nextInt(2) + 1;
    });

    //=== EXTRA ===
    
    //Efectos visuales
    lefthand.setOnMouseEntered(e -> {
    leftView.setScaleX(1.2);
    leftView.setScaleY(1.2);
    wormViewLeft.setScaleX(1.2);
    wormViewRight.setScaleY(1.2);
    });

    lefthand.setOnMouseExited(e -> {
    leftView.setScaleX(1);
    leftView.setScaleY(1);
    wormViewLeft.setScaleX(1);
    wormViewLeft.setScaleY(1);
    });
    
    righthand.setOnMouseEntered(e -> {
       rightView.setScaleX(1.2);
       rightView.setScaleY(1.2);
       wormViewRight.setScaleX(1.2);
       wormViewRight.setScaleY(1.2);
    });
    
    righthand.setOnMouseExited(e -> {
    rightView.setScaleX(1);
    rightView.setScaleY(1);
    wormViewRight.setScaleX(1);
    wormViewRight.setScaleY(1);
    });
    
    
    
    
    //Escena
       Scene escena = new Scene(escenaFinal); //Parametros de la ventana
        
       escena.setOnKeyPressed(e -> {

        if (e.getCode() == KeyCode.O){ //atajo bossfight
            
            //callar el dialogo
            viejoSonidos.stopAllDialogues();
            
        BossFight menu = new BossFight();
        menu.start(stage);
             }
        });
       
        stage.setTitle("Worms 2 The Revenge");
        stage.setScene(escena);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true); //pantalla completa
        stage.show(); //Mostrar Escena 
        transitions.fadeInBlack(escenaFinal, 2);
        dialogoIntro();
    }
    
}
