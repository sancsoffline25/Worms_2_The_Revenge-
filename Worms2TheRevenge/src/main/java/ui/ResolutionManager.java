/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Santiago Guinel
 */
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ResolutionManager {

    // Resolución base del juego (4:3)
    public static final double BASE_WIDTH = 1024;
    public static final double BASE_HEIGHT = 768;

    public static Scene crearEscena(Parent contenido) {

        // Pantalla completa que ocupa todo el monitor
        Pane viewport = new Pane();
        viewport.setStyle("-fx-background-color: black;");

        // Contenedor que contiene el juego y permite escalarlo
        Group grupoEscalado = new Group(contenido);
        viewport.getChildren().add(grupoEscalado);

        Runnable actualizarEscala = () -> {

            double ancho = viewport.getWidth();
            double alto = viewport.getHeight();

            if (ancho <= 0 || alto <= 0) {
                return;
            }

            // Calculamos cuánto podemos agrandar el juego
            double escalaX = ancho / BASE_WIDTH;
            double escalaY = alto / BASE_HEIGHT;

            // Mantener proporción 4:3
            double escala = Math.min(escalaX, escalaY);

            grupoEscalado.setScaleX(escala);
            grupoEscalado.setScaleY(escala);

            // Tamaño que tendrá el juego después de escalarse
            double anchoJuego = BASE_WIDTH * escala;
            double altoJuego = BASE_HEIGHT * escala;

            // Centrar el juego en la pantalla
            grupoEscalado.setLayoutX(
                (ancho - anchoJuego) / 2
            );

            grupoEscalado.setLayoutY(
                (alto - altoJuego) / 2
            );
        };

        // Actualizar escala si cambia el tamaño de la ventana
        viewport.widthProperty().addListener(
            (obs, viejo, nuevo) -> actualizarEscala.run()
        );

        viewport.heightProperty().addListener(
            (obs, viejo, nuevo) -> actualizarEscala.run()
        );

        actualizarEscala.run();

        return new Scene(viewport, Color.BLACK);
    }
}