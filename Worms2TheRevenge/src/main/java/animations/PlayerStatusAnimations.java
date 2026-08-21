/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package animations;

/**
 *
 * @author Santiago Guinel
 */

import entities.Player;
import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PlayerStatusAnimations{
    
    public void mostrarDanio(Player jugador, ImageView playerView){
        
        playerView.setImage(jugador.getDamagedSprite()); //se setea el sprite dañado
        
        PauseTransition pausa = new PauseTransition(Duration.seconds(0.5));
        
        pausa.setOnFinished(e ->{
            if(jugador.getVida() <= 25){
                playerView.setImage(jugador.getTiredSprite());
            }else{
                playerView.setImage(jugador.getIdleSprite());
            }
            
        });
        
        pausa.play();
    }
    
}
