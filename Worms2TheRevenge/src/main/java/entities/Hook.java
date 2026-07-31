/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author laros
 */

import entities.HookState;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Hook{
    
    //Punto donde cuelga el anzuelo
    private final double originX;
    private final double originY;

    //Estado del anzuelo
    private final double INITIAL_LENGTH = 120;
    private double length;
    private double angle;

    //Representación gráfica
    private final Line line;
    private final Circle hookShape;
    
    //Estado del anzuelo. HookState referencia
    private HookState state = HookState.SWINGING;

    //Movimiento del péndulo
    private final double MAX_ANGLE = 45;
    private double swingSpeedDegPerSec = 80.0;
    
    //Subida y bajada del anzuelo
    private final double loweringSpeedPxPerSec = 260.0; //Alta variable, decila en voz alta
    private final double raisingSpeedPxPerSec = 520.0;
    
    //Nose, no lo toques
    private final double MAX_LENGTH = 500.0;
    private boolean movingRight = true;
    private long lastUpdateNanos = -1L;

    //Posicion del hook en la ventana
    private double hookX;
    private double hookY;
    private final double bottomLimit;
    
    public Hook(double originX, double originY, double bottomLimit){

        this.originX = originX;
        this.originY = originY;
        this.bottomLimit = bottomLimit;

        //Estado inicial
        this.length = INITIAL_LENGTH;
        this.angle = 0;

        // Creamos los elementos gráficos
        line = new Line();
        hookShape = new Circle(8);
        hookShape.setFill(Color.RED);

        //Posición inicial
        updateGraphics();
    }
    //Implementando deltatime
     public void update(long now){
        if (lastUpdateNanos < 0) {
            lastUpdateNanos = now;
            updateGraphics();
            return;
        }

        double dt = (now - lastUpdateNanos) / 1_000_000_000.0;
        lastUpdateNanos = now;

        switch (state){
            case SWINGING:
                double delta = swingSpeedDegPerSec * dt;

                if (movingRight){
                    angle += delta;
                    if (angle >= MAX_ANGLE){
                        angle = MAX_ANGLE;
                        movingRight = false;
                    }
                }else{
                    angle -= delta;
                    if (angle <= -MAX_ANGLE){
                        angle = -MAX_ANGLE;
                        movingRight = true;
                    }
                }
                break;

            case LOWERING:
                length += loweringSpeedPxPerSec * dt;
                if (hookY >= bottomLimit) {
                    state = HookState.RAISING;
                  
                }
                if (hookX <= 0 || hookX >= 800) {
                state = HookState.RAISING;
                }
                  
                break;

            case RAISING:
               
                length -= raisingSpeedPxPerSec * dt;
                
                if (length <= INITIAL_LENGTH) {
                    length = INITIAL_LENGTH;
                    state = HookState.SWINGING;

                } 
                break;
        }

        updateGraphics();
    }

    private void updateGraphics(){

        double radians = Math.toRadians(angle);

        hookX = originX + Math.sin(radians) * length;
        hookY = originY + Math.cos(radians) * length;

        line.setStartX(originX);
        line.setStartY(originY);

        line.setEndX(hookX);
        line.setEndY(hookY);

        hookShape.setCenterX(hookX);
        hookShape.setCenterY(hookY);
    }
    
    public void startLowering(){
        if(state == HookState.SWINGING){
            state = HookState.LOWERING;
        }
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
    public HookState getState(){
    return state;
    }   
    public void setState(HookState state){
    this.state = state;
    }
}

