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
    private Image idleHearthSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/player/PlayerPixel.png"));
    private Image damagedHearthSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/player/PlayerPixelDamaged.png"));
    private Image tiredHearthSprite = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/player/PlayerPixelTired.png"));
   
    //posición eje x y eje y
    private double x = 0;
    private double y = 0;
    
   //Inventario
    private int[] items= new int[3]; //sin usar de momento
    
    //=== Metodos ===
    
    //-- intentos
    public void perderIntento(){
        reintentos--;
    }
    
    public int getReintentos(){
        return reintentos;
    }
    
    public boolean sinIntentos(){
        return reintentos <= 0;
    }
    
    //-- sprites
    public Image getIdleSprite(){
        return idleHearthSprite; 
    }
    
    public Image getDamagedSprite(){
        return damagedHearthSprite;
    }
    
    public Image getTiredSprite(){
        return tiredHearthSprite; 
    }
    
    //-- vida, ataque y daño
    public int getVida(){
        return vida; 
    }
    
    public int getAtaque(){
        return ataque; 
    }
    
    public void recibirDanio(int cantidad){
        vida -= cantidad;
    }
    
    //-- movimiento
    public void mover(double dx, double dy){
        x+= dx;
        y+= dy;
    }
    
    public double getX(){
    
        return x;
    }
    
    public double getY(){
    
        return y;
    }
    
    
}
