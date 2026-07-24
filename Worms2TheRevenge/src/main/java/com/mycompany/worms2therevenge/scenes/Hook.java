/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author laros
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Hook {
    
    //Punto donde cuelga el anzuelo
    private final double originX;
    private final double originY;

    //Estado del anzuelo
    private double length;
    private double angle;

    //Representación gráfica
    private final Line line;
    private final Circle hookShape;

    public Hook(double originX, double originY) {

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

    private void updateGraphics() {

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
    public Line getLine() {
        return line;
    }

    public Circle getCircle() {
        return hookShape;
    }

    public double getAngle() {
        return angle;
    }

    public double getLength() {
        return length;
    }
    //Set tambien es clave (Mentira, no lo entiendo)
    public void setAngle(double angle) {
        this.angle = angle;
        updateGraphics();
    }

    public void setLength(double length) {
        this.length = length;
        updateGraphics();
    }

}
    

