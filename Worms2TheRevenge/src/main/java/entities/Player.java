/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import javafx.scene.image.Image;

/**
 *
 * @author Santiago Guinel
 */
public class Player {
    
    //Estadisticas Iniciales
    private int vida = 100; 
    private int reintentos = 3;
    private int ataque = 20;
    
    //Sprites
    Image idleHearthSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/player/PlayerPixel.png"));
    Image damagedHearthSprite = new Image(getClass().getResourceAsStream("Assets/Sprites/bossfight/player/PlayerPixelDamaged.png"));
    Image TiredHearthSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossifght/player/PlayerPixelTired.png"));
   
    
   //Inventario
    private int[] items= new int[3]; //sin usar de momento
    
    //Metodos
    public void perderIntento(){
        reintentos--;
    }
    
    public int getReintentos(){
        return reintentos;
    }
    
    public boolean sinIntentos(){
        return reintentos <= 0;
    }
    
    
}
