/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Lautaro Gutierrez
 */
import entities.Fish;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MiniPesca{
    //Tamaño de la ventana
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    //Punto donde está la punta de la caña
    private final double ORIGIN_X = 400;
    private final double ORIGIN_Y = 60;
    //Objetos gráficos
    private Pane gamePane;
    
    
    
    public void start(Stage stage){
       
        gamePane = new Pane();
        
        //Pez! No preguntes porque esta tan arriba
        Fish fish = new Fish(470,420);
        gamePane.getChildren().add(fish.getSprite());
        gamePane.setPrefSize(WIDTH, HEIGHT);
        //Fondo celeste
        gamePane.setStyle("-fx-background-color: lightblue;");

        Hook hook = new Hook(ORIGIN_X, ORIGIN_Y,HEIGHT - 50);
        //Referencia a Hook.java para los valores del anzuelo
        gamePane.getChildren().addAll(
                hook.getLine(),
                hook.getCircle()
        );
        
        Label titulo = new Label("Minijuego de Pesca");

        Button launchButton = new Button("Lanzar anzuelo");
        Button backButton = new Button("Volver");

        launchButton.setOnAction(e -> hook.startLowering());

        backButton.setOnAction(e -> {
            MiniGamesMenu menu = new MiniGamesMenu();
            menu.start(stage);
        });

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(launchButton, backButton);

        VBox top = new VBox(10);
        top.setAlignment(Pos.CENTER);
        top.getChildren().add(titulo);

        BorderPane root = new BorderPane();
        root.setCenter(gamePane);
        root.setTop(top);
        root.setBottom(buttons);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE){
                hook.startLowering();
            }
        });

        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();

        AnimationTimer gameLoop = new AnimationTimer(){
            @Override //@Santi, que es esto?
            public void handle(long now){
                hook.update(now);
                if(!fish.isCaptured()){
                    if(fish.isTouching(
                            hook.getHookX(),
                            hook.getHookY())){
                        fish.capture();
                        hook.catchFish();
                    }
                }
                fish.update(
                hook.getHookX(),
                hook.getHookY());
            }
            
            
        };

        gameLoop.start();
    }
    //Aca se introducen los peces
    
}