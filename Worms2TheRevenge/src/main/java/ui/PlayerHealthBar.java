/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javafx.scene.image.Image;

/**
 *
 * @author Santiago Guinel
 */
public class PlayerHealthBar{
    
    //sprites
    private Image healthBarFull = new Image(getClass().getResourceAsStream("/Assets/ui/healthbar/healthBarFull.png"));
    private Image healthBarEmpty = new Image(getClass().getResourceAsStream("/Assets/ui/healthbar/healthBarEmpty.png"));
    
    private Image healthBarMinus25 = new Image(getClass().getResourceAsStream("/Assets/ui/healthbar/healthBarFullMinus25.png"));
    private Image healthBarMinus50 = new Image(getClass().getResourceAsStream("/Assets/ui/healthbar/healthBarFullMinus50.png"));
    private Image healthBarMinus75 = new Image(getClass().getResourceAsStream("/Assets/ui/healthbar/healthBarFullMinus75.png"));
    
    //Metodos
    public Image getHealthBarFullSprite(){
        return healthBarFull;
    }
    
    public Image getHealthBarEmptySprite(){
        return healthBarEmpty;
    }
    
    public Image getBarSpriteMinus25(){
        return healthBarMinus25;
    }
    
    public Image getBarSpriteMinus50(){
        return healthBarMinus50;
    }
    
    public Image getBarSpriteMinus75(){
        return healthBarMinus75;
    }
    
}
