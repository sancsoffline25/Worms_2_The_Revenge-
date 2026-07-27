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
    private double swingSpeedDegPerSec = 80.0;
    private final double loweringSpeedPxPerSec = 220.0; //Alta variable, decila en voz alta
    private final double MAX_LENGTH = 500.0;
    private boolean movingRight = true;
    private long lastUpdateNanos = -1L;

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
                if (length >= MAX_LENGTH){
                    length = MAX_LENGTH;
                    //Mas tarde hago que suba, quiero ir a dormir.
                }
                break;

            case RAISING:
                //Aca hago que suba. Despues.
                break;
        }

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
    

