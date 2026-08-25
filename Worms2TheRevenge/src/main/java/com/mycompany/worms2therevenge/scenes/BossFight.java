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
import javafx.animation.PauseTransition;
import javafx.util.Duration;


//Entidades
import entities.Enemy;
import entities.Player;

//Recursos(Animaciones)
import animations.EnemyAttackAnimations;
import animations.PlayerStatusAnimations;
import ui.PlayerHealthBar;
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
    
    //Barra de vida del jugador
    PlayerHealthBar playerBar = new PlayerHealthBar();
    
    //Sprites de las entidades
    ImageView playerView = new ImageView(jugador.getIdleSprite());
    ImageView viejoView = new ImageView(viejo.getIdleSprite());
    
    //Sprite barra de vida
    ImageView playerBarView = new ImageView(playerBar.getHealthBarFullSprite());
    
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
    
    //Spawners points base
    Circle spawnHorizontalRight = new Circle(5, Color.RED);
    Circle spawnHorizontalLeft = new Circle(5, Color.RED);
    
    Circle spawnVerticalTop = new Circle(5, Color.RED);
    Circle spawnVerticalDown = new Circle(5, Color.RED);
    
    //Spawners points diagonales
    Circle spawnDiagonalTopRight = new Circle(5, Color.BLUE);
    Circle spawnDiagonalTopLeft = new Circle(5, Color.BLUE);

    Circle spawnDiagonalDownRight = new Circle(5, Color.BLUE);
    Circle spawnDiagonalDownLeft = new Circle(5, Color.BLUE);
    
    //Contenedores extra
    VBox viejoContainer = new VBox(20);
    StackPane spawnerContainer = new StackPane();
    HBox hud = new HBox(20);
    
    //Booleanos
    boolean[] teclas = new boolean[4]; //esto nos va servir para generar un movimiento fluído
    boolean playerDied = false;
    
    final int ARRIBA = 0;
    final int ABAJO = 1;
    final int IZQUIERDA = 2;
    final int DERECHA = 3; //int no modificables
    
    // === FASES DE ATAQUE ===
    //Tambien les podes llamar patrones pero yo los llame asi :D 
    
    //--Primer sección de ataques--
    //en esta sección hay ataques mas básicos
    public void ataque1Fase1(StackPane escena){
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
    }
    
    public void ataque1Fase2(StackPane escena){
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 3.0, playerView, jugador, playerBar, playerBarView);
    }
    
    public void ataque1Fase3(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
    }
    
    public void ataque1Fase4(StackPane escena){
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
    }
    
    
    //--Segunda sección de ataques--
    //en esta sección hay ataques mas completos
    
    public void ataque2Fase1(StackPane escena){
    //Ataques Combinados
    
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
    
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
    
    }
    
    public void ataque2Fase2(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        
    }
    
    public void ataque2Fase3(StackPane escena){
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
    }
    
    public void ataque2Fase4(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
    }
    
    //Sincronizadas
    public void ataque2Fase5(StackPane escena){
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
        
    }
    
    public void ataque2Fase6(StackPane escena){
        ataques.ataqueDiagonalDownIzq(escena, spawnDiagonalDownLeft, spawnDiagonalTopRight, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalDownDer(escena, spawnDiagonalDownRight, spawnDiagonalTopLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopDer(escena, spawnDiagonalTopRight, spawnDiagonalDownLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopIzq(escena, spawnDiagonalTopLeft, spawnDiagonalDownRight, 3.0, playerView, jugador, playerBar, playerBarView);
    }
    //uno dificil (5 + 6 = ataquefase7)
    public void ataque2Fase7(StackPane escena){
        ataques.ataqueDiagonalDownIzq(escena, spawnDiagonalDownLeft, spawnDiagonalTopRight, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalDownDer(escena, spawnDiagonalDownRight, spawnDiagonalTopLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopDer(escena, spawnDiagonalTopRight, spawnDiagonalDownLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopIzq(escena, spawnDiagonalTopLeft, spawnDiagonalDownRight, 3.0, playerView, jugador, playerBar, playerBarView);
        
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
    }
    

    public void start(Stage stage){
        
        //Personalización de la battleBox
        battleBox.setFill(Color.BLACK);
        battleBox.setStroke(Color.WHITE);
        battleBox.setStrokeWidth(5);
        
        //Identación contenedores extras
        viejoContainer.getChildren().add(viejoView);
        hud.getChildren().add(playerBarView);
        spawnerContainer.getChildren().addAll(
                spawnHorizontalRight,
                spawnHorizontalLeft,
                spawnVerticalTop,
                spawnVerticalDown,
                spawnDiagonalTopRight,
                spawnDiagonalTopLeft,
                spawnDiagonalDownRight,
                spawnDiagonalDownLeft
        );
        
        //Contenedor principal
        StackPane escenaFinal = new StackPane();
        escenaFinal.getChildren().addAll(
                viejoContainer,
                battleBox,
                controlsView,
                playerView,
                hud,
                spawnerContainer
        );
             
        //Posicionamiento
        viejoContainer.setAlignment(Pos.TOP_CENTER);
        spawnerContainer.setAlignment(Pos.CENTER);
        hud.setAlignment(Pos.BOTTOM_CENTER);
        
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
        spawnVerticalDown.setTranslateY(270);
        
        //--Spawners Diagonales
        spawnDiagonalTopRight.setTranslateX(600);
        spawnDiagonalTopRight.setTranslateY(-270);
        
        spawnDiagonalTopLeft.setTranslateX(-600);
        spawnDiagonalTopLeft.setTranslateY(-270);
        
        spawnDiagonalDownRight.setTranslateX(600);
        spawnDiagonalDownRight.setTranslateY(270);
        
        spawnDiagonalDownLeft.setTranslateX(-600);
        spawnDiagonalDownLeft.setTranslateY(270);

         //ajustamo los sprites a su medida correspondiente
        playerView.setFitWidth(48);
        playerView.setFitHeight(46);
        
        viejoView.setFitWidth(370);
        viejoView.setFitHeight(420);
        
        viejoView.setTranslateY(10);
        
        controlsView.setFitWidth(200);
        controlsView.setFitHeight(200);
        
        playerBarView.setFitWidth(256);
        playerBarView.setFitHeight(64);
        
        escenaFinal.setStyle("-fx-background-color: black");
        
        //Escena
        Scene escena = new Scene(escenaFinal);
        
        //=== Lógica del gameplay ===
        
        
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
         
        //check, si el jugador esta muerto no se puede mover
        if(!playerDied){
            
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
        
            }
        
        //Actualización visual
        playerView.setTranslateX(jugador.getX());
        playerView.setTranslateY(jugador.getY());
        
        //Detectar muerte
        if (jugador.getVida() <= 0 && !playerDied){

            playerDied = true;

            //Detenemos las teclas
            teclas[ARRIBA] = false;
            teclas[ABAJO] = false;
            teclas[IZQUIERDA] = false;
            teclas[DERECHA] = false;

            //Animación de muerte
            statusAnimations.mostrarMuerte(jugador, playerView);
        
                }
        
            }
        
        };

         movimiento.start();
        
        //testing
        ataque2Fase7(escenaFinal);
        
        stage.setTitle("Worms 2: The Revenge");
        stage.setScene(escena);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();
    }
    
}
