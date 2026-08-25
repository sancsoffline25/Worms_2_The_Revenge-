/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sounds;

/**
 *
 * @author Santiago Guinel
 */

import javafx.scene.media.AudioClip;

public class playersounds{
    
    //Sonidos
    private final AudioClip hurts = new AudioClip(getClass().getResource("/Assets/Sonidos/SFX/hitHurt.wav").toExternalForm());
    
    //Metodos
    public void damageSoundEffect(){
        hurts.play();
    }
    
}
