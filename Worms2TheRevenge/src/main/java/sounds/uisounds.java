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

public class uisounds{
    
    private final AudioClip buttonSound = new AudioClip(getClass().getResource("/Assets/Sonidos/SFX/buttonSound.wav").toExternalForm());
    private final AudioClip buttonFocus = new AudioClip(getClass().getResource("/Assets/Sonidos/SFX/buttonSelect.wav").toExternalForm());
    
    
    //Metodos
    public void playSoundButton(){
        buttonSound.setVolume(0.3);
        buttonSound.play();
    }
    
    public void playButtonFocus(){
        buttonFocus.setVolume(0.1);
        buttonFocus.play();
    }
    
}
