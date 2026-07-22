/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Lautaro Gutierrez
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MiniPesca {

    //Anzuelo(Clave)
    private Hook hook;
    
    // Tamaño de la ventana
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    // Punto donde está la punta de la caña
    private final double ORIGIN_X = 400;
    private final double ORIGIN_Y = 60;

    // Objetos gráficos
    private Pane gamePane;

    public void start(Stage stage) {
        
        gamePane = new Pane();
        gamePane.setPrefSize(WIDTH, HEIGHT);

        // Fondo celeste
        gamePane.setStyle("-fx-background-color: lightblue;");
        
        hook = new Hook(ORIGIN_X, ORIGIN_Y);
        //Omega improtante, estos dos van a hacer que la cuerda balancee como en worms, boludea con los numeros si queres
        hook.setAngle(35);
        hook.setLength(180);
        
        //Referencia a Hook.java para los valores del anzuelo
        gamePane.getChildren().addAll(
        hook.getLine(),
        hook.getCircle()
        );
        //No tengo que explicar esto
        Label titulo = new Label("Minijuego de Pesca");

        Button backButton = new Button("Volver");

        backButton.setOnAction(e -> {

            MiniGamesMenu menu = new MiniGamesMenu();
            menu.start(stage);

        });
        //Ajustes a la ventana. Testing, despues se cambia
        VBox top = new VBox(10);

        top.setAlignment(Pos.CENTER);

        top.getChildren().addAll(titulo);

        BorderPane root = new BorderPane();

        root.setCenter(gamePane);
        root.setTop(top);
        root.setBottom(backButton);

        BorderPane.setAlignment(backButton, Pos.CENTER);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.show();

    }
    

}
