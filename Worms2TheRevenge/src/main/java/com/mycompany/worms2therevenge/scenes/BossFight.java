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
import ui.ResolutionManager;
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
    double velocidad = 6;
    
    //BattleBox
    Rectangle battleBox= new Rectangle(600, 300);
    
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
    
    // Contenedor principal de la batalla
    StackPane escenaFinal;
    
    //Contenedores extra
    VBox viejoContainer = new VBox(20);
    StackPane spawnerContainer = new StackPane();
    HBox hud = new HBox(20);
    
    //Booleanos para anim y estados
    boolean[] teclas = new boolean[4]; //esto nos va servir para generar un movimiento fluído
    boolean playerDied = false;
    
    final int ARRIBA = 0;
    final int ABAJO = 1;
    final int IZQUIERDA = 2;
    final int DERECHA = 3; //int no modificables
    
    //Estados de la pelea
    int faseActual = 1;
    int ataqueActual = 1;
    boolean turnoJugador = false;
    boolean batallaTerminada = false;
    
    // === FASES DE ATAQUE ===
    //Tambien les podes llamar patrones pero yo los llame asi :D 
    
    //--Primer sección de ataques--
    //en esta sección hay ataques mas básicos
    public void fase1Ataque1(StackPane escena){
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
    }
    
    public void fase1Ataque2(StackPane escena){
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 3.0, playerView, jugador, playerBar, playerBarView);
    }
    
    public void fase1Ataque3(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
    }
    
    public void fase1Ataque4(StackPane escena){
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
    }
    
    
    //--Segunda sección de ataques--
    //en esta sección hay ataques mas completos
    
    public void fase2Ataque1(StackPane escena){
    //Ataques Combinados
    
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
    
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
    
    }
    
    public void fase2Ataque2(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        
    }
    
    public void fase2Ataque3(StackPane escena){
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
    }
    
    public void fase2Ataque4(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
    }
    
    //Sincronizadas
    public void fase2Ataque5(StackPane escena){
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
        
    }
    
    public void fase2Ataque6(StackPane escena){
        ataques.ataqueDiagonalDownIzq(escena, spawnDiagonalDownLeft, spawnDiagonalTopRight, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalDownDer(escena, spawnDiagonalDownRight, spawnDiagonalTopLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopDer(escena, spawnDiagonalTopRight, spawnDiagonalDownLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopIzq(escena, spawnDiagonalTopLeft, spawnDiagonalDownRight, 3.0, playerView, jugador, playerBar, playerBarView);
    }
    //uno dificil (5 + 6 = ataquefase7)
    public void fase2Ataque7(StackPane escena){
        ataques.ataqueDiagonalDownIzq(escena, spawnDiagonalDownLeft, spawnDiagonalTopRight, 4.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalDownDer(escena, spawnDiagonalDownRight, spawnDiagonalTopLeft, 4.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopDer(escena, spawnDiagonalTopRight, spawnDiagonalDownLeft, 4.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopIzq(escena, spawnDiagonalTopLeft, spawnDiagonalDownRight, 4.5, playerView, jugador, playerBar, playerBarView);
        
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
    }
    
    //Metodos estructura de la pelea
    public void comenzarFase(){
        turnoJugador = false;
        
        if(ataqueActual <= 1){
            fase1Ataque4(escenaFinal);
        }else if(ataqueActual<= 2){
            fase1Ataque3(escenaFinal);
        }else if(ataqueActual <= 3){
            fase1Ataque2(escenaFinal);
        }else if(ataqueActual <= 4){
           fase1Ataque1(escenaFinal); 
        }
        
        if(faseActual > 1){
            if(ataqueActual == 1){
                fase2Ataque1(escenaFinal);
            }else if(ataqueActual == 2){
                fase2Ataque2(escenaFinal);
            }else if(ataqueActual == 3){
                fase2Ataque3(escenaFinal);
            }else if(ataqueActual == 4){
                fase2Ataque4(escenaFinal);
            }else if(ataqueActual == 5){
                fase2Ataque5(escenaFinal);
            }else if(ataqueActual == 6){
                fase2Ataque6(escenaFinal);
            }else if(ataqueActual >= 7){
                fase2Ataque7(escenaFinal);
                ataqueActual = 5;
            }
        }
    }
    
    private void terminarFase(){
        ataqueActual++;
        if(faseActual == 1 && ataqueActual > 4){
            faseActual++;
            ataqueActual = 1;
        }
        PauseTransition espera = new PauseTransition(Duration.seconds(3.5));
        
        espera.setOnFinished(e->{
            comenzarFase();
        });
        
        espera.play();
        
    }
    
    private void turnoJugador(){
    
    }
    
    private void terminarBatalla(){
        
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
        battleBox.setTranslateY(160);
        
        //== Alineamiento Spawners ==

        //--Spawners Horizontales
        spawnHorizontalRight.setTranslateX(450);
        spawnHorizontalRight.setTranslateY(160);

        spawnHorizontalLeft.setTranslateX(-450);
        spawnHorizontalLeft.setTranslateY(160);


        //--Spawners Verticales
        spawnVerticalTop.setTranslateX(0);
        spawnVerticalTop.setTranslateY(-150);

        spawnVerticalDown.setTranslateX(0);
        spawnVerticalDown.setTranslateY(400);


        //--Spawners Diagonales
        spawnDiagonalTopRight.setTranslateX(450);
        spawnDiagonalTopRight.setTranslateY(-150);

        spawnDiagonalTopLeft.setTranslateX(-450);
        spawnDiagonalTopLeft.setTranslateY(-150);

        spawnDiagonalDownRight.setTranslateX(450);
        spawnDiagonalDownRight.setTranslateY(400);

        spawnDiagonalDownLeft.setTranslateX(-450);
        spawnDiagonalDownLeft.setTranslateY(400);   
        
         //ajustamo los sprites a su medida correspondiente
        playerView.setFitWidth(40);
        playerView.setFitHeight(38);
        
        viejoView.setFitWidth(370);
        viejoView.setFitHeight(420);
        
        viejoView.setTranslateY(10);
        
        controlsView.setFitWidth(200);
        controlsView.setFitHeight(200);
        
        playerBarView.setFitWidth(256);
        playerBarView.setFitHeight(64);
        
        escenaFinal.setStyle("-fx-background-color: black");
        
        //Escena
        Scene escena = ResolutionManager.crearEscena(escenaFinal);
        
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
        jugador.limitarMovimiento(-280, 280, 31, 289);
        
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
        
        stage.setTitle("Worms 2: The Revenge");
        stage.setScene(escena);
        stage.setResizable(false);
        stage.show();
    }
    
}
