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
import ui.PlayerHealthBar;

public class PlayerStatusAnimations{
    
    public void mostrarDanio(Player jugador, ImageView playerView){
        
        playerView.setImage(jugador.getDamagedSprite()); //se setea el sprite dañado
        
        PauseTransition pausa = new PauseTransition(Duration.seconds(0.6));
        
        pausa.setOnFinished(e ->{
            if(jugador.getVida() <= 0){
                playerView.setImage(jugador.getDeathExtraFrame());
            }else if(jugador.getVida() <= 25){
                playerView.setImage(jugador.getTiredSprite());
            }else{
                playerView.setImage(jugador.getIdleSprite());
            }
            
        });
        
        pausa.play();
    }
    
    public void actualizarBarra(Player jugador, PlayerHealthBar playerBar, ImageView playerBarView){
        
        if(jugador.getVida() <= 0){
            playerBarView.setImage(playerBar.getHealthBarEmptySprite());
        }
        else if(jugador.getVida() <= 25){
            playerBarView.setImage(playerBar.getBarSpriteMinus75());
        }else if(jugador.getVida() <= 50){
            playerBarView.setImage(playerBar.getBarSpriteMinus50());
        }else if(jugador.getVida() <= 75){
            playerBarView.setImage(playerBar.getBarSpriteMinus25());
        }
    }
    
    //animación de muerte para el jugador
    public void mostrarMuerte(Player jugador, ImageView playerView) {
    
    playerView.setImage(jugador.getDeathFrame1()); //seteamos el frame1
    
    PauseTransition pausa1 = new PauseTransition(Duration.seconds(0.2)); //tiempo entre frames
    
    pausa1.setOnFinished(e -> {
        playerView.setImage(jugador.getDeathFrame2()); //frame2
        
        PauseTransition pausa2 = new PauseTransition(Duration.seconds(0.2));
        
        pausa2.setOnFinished(e2 -> {
            playerView.setImage(jugador.getDeathFrame3());//frame 3
            
            PauseTransition pausa3 = new PauseTransition(Duration.seconds(0.3));
            
            pausa3.setOnFinished(e3 ->{
                playerView.setVisible(false);
            });
            
            pausa3.play();
        });
        
        pausa2.play();
    });
    
    pausa1.play();
    }
    
}
