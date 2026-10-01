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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Fish{
    //Posicion otra ves
    private double x;
    private double y;
    
    private final FishType type;
    private final int id;
    private boolean captured = false;
    //Aca van despues los dibujos todos feos del Bilbo
    private final ImageView sprite;
    //Los peces se mueven
    private final double screenWidth = 800.0;
    private boolean movingRight = true;
    
    public Fish(int id, double x, double y, FishType type){
    this.x = x;
    this.y = y;
    this.type = type;
    this.id = id;
    //TEMPORAL!!
    var stream = getClass().getResourceAsStream(type.getSpritePath());

    if (stream == null) {
    throw new RuntimeException(
            "No se encontró el sprite: " + type.getSpritePath()
    );
}

    Image image = new Image(stream);

    sprite = new ImageView(image);
    //TEMPORAL!!
    
    
    sprite.setFitWidth(type.getSize() * 2);
    sprite.setFitHeight(type.getSize() * 2);
    sprite.setPreserveRatio(true);

    updateGraphics();
    }
    private void updateGraphics() {
    sprite.setLayoutX(x - sprite.getFitWidth() / 2);
    sprite.setLayoutY(y - sprite.getFitHeight() / 2);
}
    public ImageView getSprite() {
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
            x += type.getSpeed() * dt;
        }else{
            x -= type.getSpeed() * dt;
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
    public int getId() {
    return id;
}
    
 }

