/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Santiago Guinel
 */
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class ResolutionManager {

    //Resolución fija del juego
    public static final double BASE_WIDTH = 1366;
    public static final double BASE_HEIGHT = 768;

    public static Scene crearEscena(Parent contenido) {

        //Creamos la escena con la resolución del juego
        Scene escena = new Scene(
            contenido,
            BASE_WIDTH,
            BASE_HEIGHT,
            Color.BLACK
        );

        return escena;
    }
}