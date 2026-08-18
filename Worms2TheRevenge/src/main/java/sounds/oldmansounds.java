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

public class oldmansounds {
    
    private final AudioClip dialogueLine1 = new AudioClip(getClass().getResource("/Assets/Sonidos/Dialogos/DialogoIntro1.wav").toExternalForm());
    private final AudioClip dialogueLine2 = new AudioClip(getClass().getResource("/Assets/Sonidos/Dialogos/DialogoIntro2.wav").toExternalForm());
    private final AudioClip dialogueLine3 = new AudioClip(getClass().getResource("/Assets/Sonidos/Dialogos/DialogoIntro3.wav").toExternalForm());
    private final AudioClip dialogueLine4 = new AudioClip(getClass().getResource("/Assets/Sonidos/Dialogos/DialogoIntro4.wav").toExternalForm());
    private final AudioClip dialogueLineGO = new AudioClip(getClass().getResource("/Assets/Sonidos/Dialogos/SinReintentos.wav").toExternalForm()); 
    
    public void playDialogue1(){
        dialogueLine1.play();
    }
    
    public void playDialogue2(){
        dialogueLine2.play();
    }
    
    public void playDialogue3(){
        dialogueLine3.play();
    }
    
    public void playDialogue4(){
        dialogueLine4.play();
    }
    
    public void playDialogueGO(){
        dialogueLineGO.play();
    }
    
}
