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
    
    PlayerHealthBar playerBarAnim = new PlayerHealthBar();
    
    public void mostrarDanio(Player jugador, ImageView playerView){
        
        playerView.setImage(jugador.getDamagedSprite()); //se setea el sprite dañado
        
        PauseTransition pausa = new PauseTransition(Duration.seconds(0.6));
        
        pausa.setOnFinished(e ->{
            if(jugador.getVida() <= 25){
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
    
}
