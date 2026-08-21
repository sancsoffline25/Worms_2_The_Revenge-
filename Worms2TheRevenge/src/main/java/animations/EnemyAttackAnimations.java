/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package animations;

import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;


//entidades afectadas
import entities.Player;

/**
 *
 * @author Santiago Guinel
 */
public class EnemyAttackAnimations {
    
    //=== Atributos de la clase ===
    
    //Sprites Manos
    private Image manoIzquierda = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/leftHand.png"));
    private Image manoDerecha = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/rightHand.png"));
    private Image manoIzqHorizontal = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/horizontalLH.png"));
    private Image manoDerHorizontal = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/horizontalRH.png"));
    private Image manoIzqVertical = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/verticalDownLH.png"));
    private Image manoDerVertical = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/verticalDownRH.png"));
        
    //=== Animaciones de Ataque ===
    
    //estados del jugador
    PlayerStatusAnimations statusAnimations = new PlayerStatusAnimations();
    
    //--Ataque horizontal derecha
    public void ataqueHorizontalDer(
            StackPane escena, 
            Circle spawnInicio, 
            Circle spawnFinal, 
            double duracionAtaque,
            ImageView playerView, 
            Player jugador){
        
        ImageView mano = new ImageView(manoDerHorizontal);
        
        //Animacion movimiento
        TranslateTransition ataque = new TranslateTransition(Duration.seconds(duracionAtaque), mano);
        
        escena.getChildren().add(mano);
        
        
        //Points2D
        Point2D inicio = escena.sceneToLocal(
        spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
        spawnFinal.localToScene(0, 0)
        );

        //Posición a la que deben moverse
        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());
        
        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());
        
        //este animation timer va a funcionar como una collisionshape o un area2D tipico
        AnimationTimer colision = new AnimationTimer() {

        @Override
        public void handle(long ahora) {

            if (mano.getBoundsInParent().intersects(playerView.getBoundsInParent())){
            jugador.recibirDanio(25);
            statusAnimations.mostrarDanio(jugador, playerView);

            System.out.println("el jugador fue golpeado");
            System.out.println("Vida restante: " + jugador.getVida());
            stop();
            }
         }
        };
        
        ataque.setOnFinished(e-> {
            escena.getChildren().remove(mano);
            colision.stop();
        });
        
        colision.start();
        ataque.play();
    }

    //--Ataque horizontal izquierda
    public void ataqueHorizontalIzq(
            StackPane escena, 
            Circle spawnInicio, 
            Circle spawnFinal, 
            double duracionAtaque,
            ImageView playerView, 
            Player jugador){
        
        ImageView mano = new ImageView(manoIzqHorizontal);
        
        //Animacion movimiento
        TranslateTransition ataque = new TranslateTransition(Duration.seconds(duracionAtaque), mano);
        
        escena.getChildren().add(mano);
        
        //Points2D
        Point2D inicio = escena.sceneToLocal(
        spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
        spawnFinal.localToScene(0, 0)
        );

        //Posición a la que deben moverse
        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());
        
        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());
        
        //este animation timer va a funcionar como una collisionshape o un area2D tipico
        AnimationTimer colision = new AnimationTimer() {

        @Override
        public void handle(long ahora) {

            if (mano.getBoundsInParent().intersects(playerView.getBoundsInParent())){
            jugador.recibirDanio(25);
            statusAnimations.mostrarDanio(jugador, playerView);

            System.out.println("el jugador fue golpeado");
            System.out.println("Vida restante: " + jugador.getVida());
            stop();
            }
         }
        };
        
        ataque.setOnFinished(e-> {
            escena.getChildren().remove(mano);
            colision.stop();
        });
        
        colision.start();
        ataque.play();
    }
    
    //--Ataque vertical Desde arriba
    public void ataqueVerticalTop(
            StackPane escena, 
            Circle spawnInicio, 
            Circle spawnFinal, 
            double duracionAtaque,
            ImageView playerView, 
            Player jugador){
        
        ImageView mano = new ImageView(manoDerVertical);
        
        //Animacion movimiento
        TranslateTransition ataque = new TranslateTransition(Duration.seconds(duracionAtaque), mano);
        
        escena.getChildren().add(mano);
        
        //Points2D
        Point2D inicio = escena.sceneToLocal(
        spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
        spawnFinal.localToScene(0, 0)
        );

        //Posición a la que deben moverse
        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());
        
        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());
        
        //este animation timer va a funcionar como una collisionshape o un area2D tipico
        AnimationTimer colision = new AnimationTimer() {

        @Override
        public void handle(long ahora) {

            if (mano.getBoundsInParent().intersects(playerView.getBoundsInParent())){
            jugador.recibirDanio(25);
            statusAnimations.mostrarDanio(jugador, playerView);

            System.out.println("el jugador fue golpeado");
            System.out.println("Vida restante: " + jugador.getVida());
            stop();
            }
         }
        };
        
        ataque.setOnFinished(e-> {
            escena.getChildren().remove(mano);
            colision.stop();
        });
        
        colision.start();
        ataque.play();
    }
    
    public void ataqueVerticalDown(
            StackPane escena, 
            Circle spawnInicio, 
            Circle spawnFinal, 
            double duracionAtaque,
            ImageView playerView, 
            Player jugador){
        
        ImageView mano = new ImageView(manoIzqVertical);
        
        //Animacion movimiento
        TranslateTransition ataque = new TranslateTransition(Duration.seconds(duracionAtaque), mano);
        
        escena.getChildren().add(mano);
        
        //Points2D
        Point2D inicio = escena.sceneToLocal(
        spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
        spawnFinal.localToScene(0, 0)
        );

        //Posición a la que deben moverse
        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());
        
        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());
        
        //este animation timer va a funcionar como una collisionshape o un area2D tipico
        AnimationTimer colision = new AnimationTimer() {

        @Override
        public void handle(long ahora) {

            if (mano.getBoundsInParent().intersects(playerView.getBoundsInParent())){
            jugador.recibirDanio(25);
            statusAnimations.mostrarDanio(jugador, playerView);

            System.out.println("el jugador fue golpeado");
            System.out.println("Vida restante: " + jugador.getVida());
            stop();
            }
         }
        };
        
        ataque.setOnFinished(e-> {
            escena.getChildren().remove(mano);
            colision.stop();
        });
        
        colision.start();
        ataque.play();
    }

    
}

    
