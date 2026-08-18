/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package animations;

/**
 *
 * @author Santiago Guinel
 */


import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ScreenTransitions {
    
    //=== Fade In y out respectivamente ===
    
    //--Fade in negro
    public static FadeTransition fadeInBlack(StackPane escena, double segundos){ 
        
        Rectangle fade = new Rectangle(
                escena.getWidth(),
                escena.getHeight(),
                Color.BLACK
        );
        fade.setMouseTransparent(true);
        escena.getChildren().add(fade);
        
        FadeTransition fadeIn = new FadeTransition(
                Duration.seconds(segundos),
                fade
        );
        
        fadeIn.setFromValue(1);
        fadeIn.setToValue(0);
        
        fadeIn.setOnFinished(e ->{
            escena.getChildren().remove(fade);
        });
        fadeIn.play();
        
        return fadeIn; 
    }
    
    //--Fade out negro
    public static FadeTransition fadeOutBlack(StackPane escena, double segundos){ 
        
        Rectangle fade = new Rectangle(
                escena.getWidth(),
                escena.getHeight(),
                Color.BLACK
        );
        fade.setMouseTransparent(true);
        escena.getChildren().add(fade);
        
        FadeTransition fadeOut = new FadeTransition(
                Duration.seconds(segundos),
                fade
        );
        
        fadeOut.setFromValue(0);
        fadeOut.setToValue(1);
        
        fadeOut.setOnFinished(e ->{
            //no pasa absolutamente nada
        });
        fadeOut.play();
        
        return fadeOut;
    }
    
    //--Fade in blanco
     public static FadeTransition fadeInWhite(StackPane escena, double segundos){ 
        
        Rectangle fade = new Rectangle(
                escena.getWidth(),
                escena.getHeight(),
                Color.WHITE
        );
        fade.setMouseTransparent(true);
        escena.getChildren().add(fade);
        
        FadeTransition fadeIn = new FadeTransition(
                Duration.seconds(segundos),
                fade
        );
        
        fadeIn.setFromValue(1);
        fadeIn.setToValue(0);
        
        fadeIn.setOnFinished(e ->{
            escena.getChildren().remove(fade);
        });
        fadeIn.play();
        
        return fadeIn; 
    }
    
     //--Fade out blanco
      public static FadeTransition fadeOutWhite(StackPane escena, double segundos){ 
        
        Rectangle fade = new Rectangle(
                escena.getWidth(),
                escena.getHeight(),
                Color.WHITE
        );
        fade.setMouseTransparent(true);
        escena.getChildren().add(fade);
        
        FadeTransition fadeOut = new FadeTransition(
                Duration.seconds(segundos),
                fade
        );
        
        fadeOut.setFromValue(0);
        fadeOut.setToValue(1);
        
        fadeOut.setOnFinished(e ->{
            //no pasa absolutamente nada
        });
        fadeOut.play();
        
        return fadeOut;
    }
     
}
