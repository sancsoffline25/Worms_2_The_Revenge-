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
    
    //Animaciones de Ataque
    public void ataqueHorizontal(StackPane escena, Circle spawnInicio, Circle spawnFinal){
        
        ImageView mano = new ImageView(manoDerHorizontal);
        
        //Animacion movimiento
        TranslateTransition ataque = new TranslateTransition(Duration.seconds(2.5), mano);
        
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
        
        ataque.setOnFinished(e-> {
            escena.getChildren().remove(mano);
        });
        
        ataque.play();
    }
    
    
}
