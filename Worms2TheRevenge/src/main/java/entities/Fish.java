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
    
    private final FishType type;

    private boolean captured = false;
    //Aca van despues los dibujos todos feos del Bilbo
    private Circle sprite;
    //Los peces se mueven
    private final double movementSpeed = 100.0;
    private final double screenWidth = 800.0;
    private boolean movingRight = true;
    
    public Fish(double x, double y, FishType type){
    this.x = x;
    this.y = y;
    this.type = type;
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
    public void update(double hookX, double hookY, double dt) {

    if (captured) {

        x = hookX;
        y = hookY;

    } else {
        if (movingRight) {
            x += movementSpeed * dt;
        }else{
            x -= movementSpeed * dt;
        }
        checkScreenBounds();
    }
    updateGraphics();
    }
    private void checkScreenBounds(){
    if (movingRight && x > screenWidth + 20) {
        x = -20;
    }else{ 
        if (!movingRight && x < -20) {
        x = screenWidth + 20;
    }}}
    public void setMovingRight(boolean movingRight) {
    this.movingRight = movingRight;
    }
    public boolean isMovingRight() {
    return movingRight;
    }
    public FishType getType() {
    return type;
    }
    public int getValue() {
    return type.getValue();
    }
    public int getDifficulty() {
    return type.getDifficulty();
    }
 }

