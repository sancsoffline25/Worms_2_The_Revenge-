/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;


import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

//Entidades
import entities.Enemy;
import entities.Player;

//Recursos(Animaciones)
import animations.EnemyAttackAnimations;
import animations.PlayerStatusAnimations;

/**
 *
 * @author Santiago Guinel
 */
public class BossFight{
    
    //=== Atributos de la clase ===
    
    //Traemos a las entidades protagonistas
    Player jugador = new Player();
    Enemy viejo = new Enemy();
    EnemyAttackAnimations viejoAnim = new EnemyAttackAnimations();
    
    //Sprites de las entidades
    ImageView playerView = new ImageView(jugador.getIdleSprite());
    ImageView viejoView = new ImageView(viejo.getIdleSprite());
    
    //Sprite controles
    Image controles = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/controls/controls.png"));
    ImageView controlsView = new ImageView(controles);
    
    //Animaciones de ataque(Viejo)
    EnemyAttackAnimations ataques = new EnemyAttackAnimations();
    
    //Animaciones de los estados del jugador
    PlayerStatusAnimations statusAnimations = new PlayerStatusAnimations();
    
    //Velocidad del jugador
    double velocidad = 5;
    
    //BattleBox
    Rectangle battleBox= new Rectangle(800, 400);
    
    //Spawners points
    Circle spawnHorizontalRight = new Circle(5, Color.RED);
    Circle spawnHorizontalLeft = new Circle(5, Color.RED);
    
    Circle spawnVerticalTop = new Circle(5, Color.GREEN);
    Circle spawnVerticalDown = new Circle(5, Color.RED);
    
    //Contenedores extra
    VBox viejoContainer = new VBox(20);
    HBox controlsContainer = new HBox(90);
    StackPane spawnerContainer = new StackPane();
    
    //Booleano para las teclas
    boolean[] teclas = new boolean[4]; //esto nos va servir para generar un movimiento fluído
    
    final int ARRIBA = 0;
    final int ABAJO = 1;
    final int IZQUIERDA = 2;
    final int DERECHA = 3; //int no modificables
    
    

    public void start(Stage stage){
        
        //Personalización de la battleBox
        battleBox.setFill(Color.BLACK);
        battleBox.setStroke(Color.WHITE);
        battleBox.setStrokeWidth(5);
        
        //Identación contenedores extras
        viejoContainer.getChildren().add(viejoView);
        controlsContainer.getChildren().add(controlsView);
        spawnerContainer.getChildren().addAll(
                spawnHorizontalRight,
                spawnHorizontalLeft,
                spawnVerticalTop,
                spawnVerticalDown
        );
        
        //Contenedor principal
        StackPane escenaFinal = new StackPane();
        escenaFinal.getChildren().addAll(
                viejoContainer,
                battleBox,
                controlsView,
                playerView,
                spawnerContainer
        );
        
        //Posicionamiento
        viejoContainer.setAlignment(Pos.TOP_CENTER);
        controlsContainer.setAlignment(Pos.CENTER_LEFT);
        spawnerContainer.setAlignment(Pos.CENTER);
        
        controlsView.setTranslateX(-800);
        
        //== Alineamiento Spawners ==
        
        //--Spawners Horizontales
        spawnHorizontalRight.setTranslateX(600);
        spawnHorizontalRight.setTranslateY(0);
        
        spawnHorizontalLeft.setTranslateX(-600);
        spawnHorizontalLeft.setTranslateY(0);
        
        //--Spawners Verticales
        spawnVerticalTop.setTranslateX(0);
        spawnVerticalTop.setTranslateY(-270);
        
        spawnVerticalDown.setTranslateX(0);
        spawnVerticalDown.setTranslateY(450);

         //ajustamo los sprites a su medida correspondiente
        playerView.setFitWidth(48);
        playerView.setFitHeight(46);
        
        viejoView.setFitWidth(370);
        viejoView.setFitHeight(420);
        
        viejoView.setTranslateY(10);
        
        controlsView.setFitWidth(64);
        controlsView.setFitHeight(64);
        
        escenaFinal.setStyle("-fx-background-color: black");
        
        //Escena
        Scene escena = new Scene(escenaFinal);
        
        //=== Lógica del game play ===
        
        
        //--movimiento del jugador
        
        //Cuando presiona la tecla
        escena.setOnKeyPressed(e -> {

            switch (e.getCode()){

        case W:
        case UP:
            teclas[ARRIBA] = true;
            break;

        case S:
        case DOWN:
            teclas[ABAJO] = true;
            break;

        case A:
        case LEFT:
            teclas[IZQUIERDA] = true;
            break;

        case D:
        case RIGHT:
            teclas[DERECHA] = true;
            break;
             }
        });
        
        //Cuando suelta la tecla
        escena.setOnKeyReleased(e -> {

        switch (e.getCode()) {

        case W:
        case UP:
            teclas[ARRIBA] = false;
            break;

        case S:
        case DOWN:
            teclas[ABAJO] = false;
            break;

        case A:
        case LEFT:
            teclas[IZQUIERDA] = false;
            break;

        case D:
        case RIGHT:
            teclas[DERECHA] = false;
            break;
             }
        });
        
        //Este AnimationTimer va a fucionar como un "_process" de Godot
        //Va a actualizar el movimiento continuamente
        //Lo que da la ilusión de ser mas fluído
        AnimationTimer movimiento = new AnimationTimer(){

        @Override
        public void handle(long ahora){

        if (teclas[ARRIBA]) {
            jugador.mover(0, -velocidad);
        }

        if (teclas[ABAJO]) {
            jugador.mover(0, velocidad);
        }

        if (teclas[IZQUIERDA]) {
            jugador.mover(-velocidad, 0);
        }

        if (teclas[DERECHA]) {
            jugador.mover(velocidad, 0);
        }
        
        //Colision del jugador
        jugador.limitarMovimiento(-376, 376, -177, 177);
        
        //Actualización visual
        playerView.setTranslateX(jugador.getX());
        playerView.setTranslateY(jugador.getY());
            }  
        
        };

         movimiento.start();
    
        //Animación de ataque(testing)
        ataques.ataqueHorizontalDer(escenaFinal, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador);
        ataques.ataqueVerticalDown(escenaFinal, spawnVerticalTop, spawnVerticalDown, 4.5, playerView, jugador);
        
        
        stage.setTitle("Worms 2: The Revenge");
        stage.setScene(escena);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();
    }
    
}
