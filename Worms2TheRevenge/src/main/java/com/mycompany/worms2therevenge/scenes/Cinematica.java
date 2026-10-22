/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Santiago Guinel
 */

import entities.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Cinematica{
   private final String RUTA = "/Assets/Cinematics/PreBossFight/frame";

    public void start(Stage stage, Player jugador){

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");

        ImageView imagen = new ImageView();
        imagen.setFitWidth(1366);
        imagen.setFitHeight(768);
        imagen.setPreserveRatio(false);

        root.getChildren().add(imagen);

        Scene scene = new Scene(root, 1366, 768, Color.BLACK);

        stage.setScene(scene);
        stage.setWidth(1366);
        stage.setHeight(768);
        stage.setResizable(false);
        stage.show();

        Timeline animacion = new Timeline();

        // Mostramos los 40 fotogramas a 6 FPS.
        for (int i = 0; i < 40; i++) {

            final int fotograma = i;

            KeyFrame frame = new KeyFrame(
                    Duration.seconds(fotograma / 6.0),
                    e -> {
                        String numero = String.format("%03d", fotograma);
                        String ruta = RUTA + numero + ".png";

                        var recurso = getClass().getResource(ruta);

                        if (recurso != null) {
                            Image imagenFrame = new Image(
                                    recurso.toExternalForm()
                            );
                            imagen.setImage(imagenFrame);
                        } else {
                            System.out.println(
                                    "No se encontró la imagen: " + ruta
                            );
                        }
                    }
            );

            animacion.getKeyFrames().add(frame);
        }

        //Esperamos hasta completar los 40 fotogramas.
        animacion.getKeyFrames().add(
                new KeyFrame(Duration.seconds(40.0 / 6.0))
        );

        animacion.setCycleCount(1);

        animacion.setOnFinished(e -> {
            BossFight boss = new BossFight();
            boss.start(stage, jugador);
        });

        animacion.play();
    } 
}
