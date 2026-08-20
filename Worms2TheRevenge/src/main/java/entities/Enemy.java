/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Santiago GUi
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {

//stats
 private int vida = 200;
 private int ataque = 25; 

 
 //sprites
 private Image idleEnemy = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/viejopixel/oldmanidlepixel.png"));
 private Image DamageEnemy = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/viejopixel/oldmanDamaged.png"));
 private Image veryDamagedEnemy = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/viejopixel/oldmanidleVeryDamaged.png"));
 private Image veryDamagedEnemyTalk = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/viejopixel/oldmanidleVeryDamagedTalk.png"));
 
 //=== Metodos ===
 
 //-- Sprites
 public Image getIdleSprite(){
     return idleEnemy;
 }

 public Image getDamageSprite(){
     return DamageEnemy;
 }
 
 public Image getVeryDamagedSprite(){
     return veryDamagedEnemy;
 }
 
 public Image getLastSprite(){
     return veryDamagedEnemyTalk;
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
 
 
}
