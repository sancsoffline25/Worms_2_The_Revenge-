/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author laros
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Fish{
    //Posicion otra ves
    private double x;
    private double y;

    private boolean captured = false;
    //Aca van despues los dibujos todos feos del Bilbo
    private Circle sprite;
    
    public Fish(double x, double y){
    this.x = x;
    this.y = y;
    sprite = new Circle(12);
    sprite.setFill(Color.GREEN);

    updateGraphics();
    }
    private void updateGraphics(){
    sprite.setCenterX(x);
    sprite.setCenterY(y);
    }
    public Circle getSprite(){
    return sprite;
    }
    public boolean isTouching(double hookX, double hookY){
    double dx = hookX - x;
    double dy = hookY - y;
    double distance = Math.sqrt(dx*dx + dy*dy);
    return distance < 20;
    }
    public void capture(){
    captured = true;
    }
    public boolean isCaptured(){
    return captured;
    }
    public void update(double hookX, double hookY){
    if(captured){
    x = hookX;
    y = hookY;
    
    updateGraphics();
    }}
    
}

