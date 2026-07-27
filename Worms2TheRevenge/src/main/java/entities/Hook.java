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
import javafx.scene.shape.Line;

public class Hook{
    
    //Punto donde cuelga el anzuelo
    private final double originX;
    private final double originY;

    //Estado del anzuelo
    private double length;
    private double angle;

    //Representación gráfica
    private final Line line;
    private final Circle hookShape;
    
    // Estado del anzuelo. HookState referencia
    private HookState state = HookState.SWINGING;

    // Movimiento del péndulo
    private final double MAX_ANGLE = 45;
    private double swingSpeed = 1.2;
    private boolean movingRight = true;

    public Hook(double originX, double originY){

        this.originX = originX;
        this.originY = originY;

        //Estado inicial
        this.length = 120;
        this.angle = 0;

        // Creamos los elementos gráficos
        line = new Line();

        hookShape = new Circle(8);
        hookShape.setFill(Color.RED);

        //Posición inicial
        updateGraphics();
    }

    private void updateGraphics(){

        double radians = Math.toRadians(angle);

        double endX = originX + Math.sin(radians) * length;
        double endY = originY + Math.cos(radians) * length;

        line.setStartX(originX);
        line.setStartY(originY);

        line.setEndX(endX);
        line.setEndY(endY);

        hookShape.setCenterX(endX);
        hookShape.setCenterY(endY);
    }
    //Get es clave
    public Line getLine(){
        return line;
    }

    public Circle getCircle(){
        return hookShape;
    }

    public double getAngle(){
        return angle;
    }

    public double getLength(){
        return length;
    }
    //Set tambien es clave (Ya lo entendi)
    public void setAngle(double angle){
        this.angle = angle;
        updateGraphics();
    }

    public void setLength(double length){
        this.length = length;
        updateGraphics();
    }

    public void update(){

    switch (state) {
        case SWINGING: //<-- Lo que dijo
            if (movingRight) {
                angle += swingSpeed;
                if (angle >= MAX_ANGLE){
                    angle = MAX_ANGLE;
                    movingRight = false;

                }

            } else {

                angle -= swingSpeed;

                if (angle <= -MAX_ANGLE){
                    angle = -MAX_ANGLE;
                    movingRight = true;
                }
            }
            break;
            
        case LOWERING:
            // Se termina despues
            break;
            
        case RAISING:
            // Se termina despues
            break;
    }
    updateGraphics();
}
    public HookState getState(){
    return state;
    }   
    public void setState(HookState state){
    this.state = state;
    }
}
    

