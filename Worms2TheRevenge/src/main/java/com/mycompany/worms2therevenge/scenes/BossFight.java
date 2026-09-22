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
import animations.EnemyStatusAnimations;
import animations.PlayerStatusAnimations;
import animations.ScreenTransitions;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import ui.ButtonCreator;
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
    
    
    //Animaciones de ataque(Viejo)
    EnemyAttackAnimations ataques = new EnemyAttackAnimations();
    
    //Animaciones de los estados del jugador
    PlayerStatusAnimations statusAnimations = new PlayerStatusAnimations();
    
    //Animaciones del enemigo(Viejo)
    EnemyStatusAnimations viejoEstados = new EnemyStatusAnimations();
    
    //Velocidad del jugador
    double velocidad = 6.7;
    
    //Botón de ataque
    VBox ataqueHud = new VBox(5);
    Label timeToAttack = new Label("2.5");
    ButtonCreator buttonMaker = new ButtonCreator();
    Font botonFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 28);
    StackPane botonAtacar = buttonMaker.crearBoton("¡ ATACAR !", botonFont);

    //Transiciones
    ScreenTransitions transiciones = new ScreenTransitions();
    
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
    double tiempoAtaques = 3.5;
    double tiempoEntreEtapas = 2.0;
    int etapaPelea = 1;
    
    // === FASES DE ATAQUE ===
    //Tambien les podes llamar patrones pero yo los llame asi :D 
    
    //--Primer sección de ataques--
    //en esta sección hay ataques mas básicos
    public void fase1Ataque1(StackPane escena){
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        espera.setOnFinished(e ->{
            if(etapaPelea ==4){
                tiempoAtaques = 2.0;
                fase2Ataque1(escenaFinal);
            }else{
                fase1Ataque2(escenaFinal);
            }
        });
        espera.play();
    }
    
    public void fase1Ataque2(StackPane escena){
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 3.0, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        espera.setOnFinished(e ->{
            fase1Ataque3(escenaFinal);
        });
        espera.play();
    }
    
    public void fase1Ataque3(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        espera.setOnFinished(e ->{
            fase1Ataque4(escenaFinal);
        });
        espera.play();
    }
    
    public void fase1Ataque4(StackPane escena){
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoEntreEtapas));
        
        espera.setOnFinished(e ->{
        //Aparece el botón para atacar
            ataqueHud.setVisible(true);

            PauseTransition ventanaAtaque =
            new PauseTransition(Duration.seconds(3.5));

            ventanaAtaque.setOnFinished(e2 ->{

                ataqueHud.setVisible(false); //ocultamos el botons

                etapaPelea += 1;

                if(etapaPelea == 3){
                    tiempoAtaques = 1.5;
                }else{
                    tiempoAtaques = 2.0;
                }
            fase1Ataque1(escenaFinal);
            
            });

            ventanaAtaque.play();
        });
        
        espera.play();
    }
    
    
    //--Segunda sección de ataques--
    //en esta sección hay ataques mas completos
    
    public void fase2Ataque1(StackPane escena){
    //Ataques Combinados
        
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
    
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        tiempoAtaques = 3.5;
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        espera.setOnFinished(e ->{
            fase2Ataque2(escenaFinal);
        });
        espera.play();
        
    }
    
    public void fase2Ataque2(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        espera.setOnFinished(e ->{
            fase2Ataque3(escenaFinal);
        });
        espera.play();
    }
    
    public void fase2Ataque3(StackPane escena){
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        espera.setOnFinished(e ->{
            fase2Ataque4(escenaFinal);
        });
        espera.play();
    }
    
    public void fase2Ataque4(StackPane escena){
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        
        espera.setOnFinished(e ->{
            fase2Ataque5(escenaFinal);
        });
        espera.play();
    }
    
    //Sincronizadas
    public void fase2Ataque5(StackPane escena){
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 3.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 2.5, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        
        espera.setOnFinished(e ->{
            fase2Ataque6(escenaFinal);
        });
        espera.play();
    }
    
    public void fase2Ataque6(StackPane escena){
        ataques.ataqueDiagonalDownIzq(escena, spawnDiagonalDownLeft, spawnDiagonalTopRight, 3.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalDownDer(escena, spawnDiagonalDownRight, spawnDiagonalTopLeft, 3.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopDer(escena, spawnDiagonalTopRight, spawnDiagonalDownLeft, 3.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopIzq(escena, spawnDiagonalTopLeft, spawnDiagonalDownRight, 3.5, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        
        espera.setOnFinished(e ->{
            fase2Ataque7(escenaFinal);
        });
        espera.play();
    }
    //uno dificil (5 + 6 = ataquefase7)
    public void fase2Ataque7(StackPane escena){
        ataques.ataqueDiagonalDownIzq(escena, spawnDiagonalDownLeft, spawnDiagonalTopRight, 4.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalDownDer(escena, spawnDiagonalDownRight, spawnDiagonalTopLeft, 4.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopDer(escena, spawnDiagonalTopRight, spawnDiagonalDownLeft, 4.5, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueDiagonalTopIzq(escena, spawnDiagonalTopLeft, spawnDiagonalDownRight, 4.5, playerView, jugador, playerBar, playerBarView);
        
        ataques.ataqueHorizontalIzq(escena, spawnHorizontalLeft, spawnHorizontalRight, 2.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueHorizontalDer(escena, spawnHorizontalRight, spawnHorizontalLeft, 2.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalTop(escena, spawnVerticalTop, spawnVerticalDown, 2.0, playerView, jugador, playerBar, playerBarView);
        ataques.ataqueVerticalDown(escena, spawnVerticalDown, spawnVerticalTop, 1.0, playerView, jugador, playerBar, playerBarView);
        
        PauseTransition espera = new PauseTransition(Duration.seconds(tiempoAtaques));
        
        espera.setOnFinished(e ->{
            //Aparece el botón para atacar
            ataqueHud.setVisible(true);

            PauseTransition ventanaAtaque =
            new PauseTransition(Duration.seconds(3.0));

            ventanaAtaque.setOnFinished(e2 ->{

                ataqueHud.setVisible(false); //ocultamos el botons
            
            fase2Ataque1(escenaFinal);
            });

            ventanaAtaque.play();
        });
        espera.play();
    }
    
    
    
    public void start(Stage stage){
        
        //OcultarSpawnPoints
        spawnHorizontalRight.setVisible(false);
        spawnHorizontalLeft.setVisible(false);
        spawnVerticalTop.setVisible(false);
        spawnVerticalDown.setVisible(false);
        spawnDiagonalTopRight.setVisible(false);
        spawnDiagonalTopLeft.setVisible(false);
        spawnDiagonalDownRight.setVisible(false);
        spawnDiagonalDownLeft.setVisible(false);
              
        //Personalización boton atacar
        timeToAttack.setTextFill(Color.WHITE); 
        
        //Personalización de la battleBox
        battleBox.setFill(Color.BLACK);
        battleBox.setStroke(Color.WHITE);
        battleBox.setStrokeWidth(5);
        
        //contenedor esencial
        escenaFinal = new StackPane();
        
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
        
        ataqueHud.getChildren().addAll(
                botonAtacar,
                timeToAttack
        );
        
        //-Identación y formación de la escenaFinal
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
                playerView,
                hud,
                spawnerContainer,
                ataqueHud
        );
             
        //Posicionamiento
        viejoContainer.setAlignment(Pos.TOP_CENTER);
        spawnerContainer.setAlignment(Pos.CENTER);
        hud.setAlignment(Pos.BOTTOM_CENTER);
        ataqueHud.setAlignment(Pos.CENTER_RIGHT);
        
        //Ubicamos el botón de ataque
        ataqueHud.setTranslateX(450);
        ataqueHud.setTranslateY(160);
        //Ocultamos el botón de ataque
        ataqueHud.setVisible(false);

        battleBox.setTranslateY(160);
        
        //== Alineamiento Spawners ==

        //--Spawners Horizontales
        spawnHorizontalRight.setTranslateX(450);
        spawnHorizontalRight.setTranslateY(110);

        spawnHorizontalLeft.setTranslateX(-450);
        spawnHorizontalLeft.setTranslateY(110);


        //--Spawners Verticales
        spawnVerticalTop.setTranslateX(-50);
        spawnVerticalTop.setTranslateY(-150);

        spawnVerticalDown.setTranslateX(-50);
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
        
        playerBarView.setFitWidth(256);
        playerBarView.setFitHeight(64);
        
        escenaFinal.setStyle("-fx-background-color: black");
        
        //Escena
        Scene escena = ResolutionManager.crearEscena(escenaFinal);
        
         //Musica
        Media musica = new Media(getClass().getResource("/Assets/Musica/BossFightPreSong.mp3").toExternalForm()); //cargo la musica
        MediaPlayer reproductor = new MediaPlayer(musica); //creo el reproductor que va a reproducirla
        reproductor.setVolume(0.3); //volumen tranqui
        reproductor.setCycleCount(MediaPlayer.INDEFINITE); //hago que este en loop
        reproductor.play(); //arranca el temón
        
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
            ataques.detenerSonidos();
            
            //Detenemos las teclas
            teclas[ARRIBA] = false;
            teclas[ABAJO] = false;
            teclas[IZQUIERDA] = false;
            teclas[DERECHA] = false;

            //Animación de muerte
            statusAnimations.mostrarMuerte(jugador, playerView);
            
            //Esperamos antes de ir al Game Over
            PauseTransition esperaGameOver =
            new PauseTransition(Duration.seconds(1));

            esperaGameOver.setOnFinished(e -> {

            FadeTransition transicion =
                transiciones.fadeOutBlack(escenaFinal, 2);
            
             transicion.setOnFinished(e2 ->{
            reproductor.stop(); 
            GameOver gameOver = new GameOver();
            gameOver.start(stage);
                    });

                    transicion.play();
                });

                esperaGameOver.play();
                }
        
            }
        
        };

         movimiento.start();
        
         
        stage.setTitle("Worms 2: The Revenge");
        stage.setScene(escena);
        stage.setResizable(false);
        stage.show();
        
        //=== Estructura de Pelea ===
         
         //Primera etapa
                fase1Ataque1(escenaFinal);
                    
         botonAtacar.setOnMouseClicked(e->{
             System.out.println("atacamos bien epico al boss");
             viejo.recibirDanio(20);
             viejoEstados.mostrarDanio(viejo, viejoView);
             ataqueHud.setVisible(false);
             System.out.println("Vida del boss: " + viejo.getVida());
             
             //Chequeo si el jefe esta vivo
            if(viejo.getVida() <= 0){
            ataques.detenerSonidos();
            FadeTransition transicion = transiciones.fadeOutBlack(escenaFinal, 2);

            transicion.setOnFinished(e2 -> {
                reproductor.stop();
                EpicEnding menu = new EpicEnding();
                menu.start(stage);
             });
            transicion.play();
            }
            
         }); 
        
         
        
        
         
    }
    
}
