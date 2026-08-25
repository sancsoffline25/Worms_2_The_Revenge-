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
    
    //sprites de muerte
    private Image deathFrame1 = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/player/PlayerDieFrame1.png"));
    private Image deathFrame2 = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/player/PlayerDieFrame2.png"));
    private Image deathFrame3 = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/player/PlayerDieFrame3.png"));
    private Image deathFrame4 = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/player/PlayerDieFrameExtra.png"));
   
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
    
    public Image getDeathFrame1(){
        return deathFrame1;
    }
    
    public Image getDeathFrame2(){
        return deathFrame2;
    }
    
    public Image getDeathFrame3(){
        return deathFrame3;
    }
    
    public Image getDeathExtraFrame(){
        return deathFrame4;
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
        
        if (vida < 0){
        vida = 0;
        }
    }
    
    public boolean estaMuyHerido(){
        return vida <= 25;
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
    
    //-- colisiones con la BattleBox
    public void limitarMovimiento(
        double limiteIzquierdo,
        double limiteDerecho,
        double limiteSuperior,
        double limiteInferior){

    if(x < limiteIzquierdo){
        x = limiteIzquierdo;
    }

    if(x > limiteDerecho){
        x = limiteDerecho;
    }

    if(y < limiteSuperior){
        y = limiteSuperior;
    }

    if(y > limiteInferior){
        y = limiteInferior;
        }
    }
    
}
