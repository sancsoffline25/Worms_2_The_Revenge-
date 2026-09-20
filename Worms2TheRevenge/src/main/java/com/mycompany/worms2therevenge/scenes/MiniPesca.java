/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.worms2therevenge.scenes;

/**
 *
 * @author Lautaro Gutierrez
 */
import javafx.scene.text.Text;
import entities.Fish;
import entities.FishType;
import entities.HookState;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.ResolutionManager;
import entities.FishManager;

public class MiniPesca{
    //Tamaño de la ventana
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    //Punto donde está la punta de la caña
    private final double ORIGIN_X = 400;
    private final double ORIGIN_Y = 60;
    
    //Platita
    private int playerMoney = 0;
    //Objetos gráficos
    private Pane gamePane;
    
    public void start(Stage stage){
       
        gamePane = new Pane();
        
       //No hay mas pez
        gamePane.setPrefSize(WIDTH, HEIGHT);
        //Fondo celeste
        gamePane.setStyle("-fx-background-color: lightblue;");
        
        //Display de dinero
        Text moneyText = new Text();
        moneyText.setText("Dinero: $0");
        moneyText.setX(20);
        moneyText.setY(30);
        moneyText.setStyle("-fx-font-size: 20px;");
        gamePane.getChildren().add(moneyText);
        
        
        Hook hook = new Hook(ORIGIN_X, ORIGIN_Y,HEIGHT - 50);
        //Referencia a Hook.java para los valores del anzuelo
        
        gamePane.getChildren().addAll(
                hook.getLine(),
                hook.getCircle()
        );
        
        FishManager fishManager = new FishManager(WIDTH);
        //Referencia a FishManager para hacer las filas
        fishManager.createRow(220, 8, 105, true, FishType.BASIC);

        fishManager.createRow(280, 4, 210, false, FishType.MID);

        fishManager.createRow(340, 8, 105, true, FishType.BASIC);
        
        //fishManager.createRow(340, 4, 210, false, FishType.BASIC);
        
        fishManager.createRow(360, 8, 30, false, FishType.SWARM);
        fishManager.createRow(380, 8, 30, true, FishType.SWARM);
        
        fishManager.createRow(420, 3, 315, false, FishType.MID);
        
        fishManager.createRow(480, 1, 105, true, FishType.BIG);
        
        //Las variables son(Profundidad, cantidad, separacion,dirccion, typo)

        // Añadir los peces al gamePane
        for (Fish fish : fishManager.getFishList()) {
        gamePane.getChildren().add(fish.getSprite());
}   
        Label titulo = new Label("Minijuego de Pesca");

        Button launchButton = new Button("Lanzar anzuelo");
        Button backButton = new Button("Volver");

        launchButton.setOnAction(e -> hook.startLowering());
        //Botones y funciones 
        backButton.setOnAction(e -> {
        GamePlayBase gamePlayBase = new GamePlayBase();
        gamePlayBase.start(stage);
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
        
        // Contenedor de resolución base
        StackPane escenaFinal = new StackPane();

        escenaFinal.setPrefSize(
        ResolutionManager.BASE_WIDTH,
        ResolutionManager.BASE_HEIGHT
        );
        //Tamaño minimo y maximo de la ventana
        escenaFinal.setMinSize(
        ResolutionManager.BASE_WIDTH,
        ResolutionManager.BASE_HEIGHT
        );

        escenaFinal.setMaxSize(
        ResolutionManager.BASE_WIDTH,
        ResolutionManager.BASE_HEIGHT
        );

        escenaFinal.getChildren().add(root);

        Scene scene = ResolutionManager.crearEscena(escenaFinal);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE){
                hook.startLowering();
            }
        });

        

        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.setResizable(false);

        stage.show();
        long[] lastFrameTime = {-1L};
        AnimationTimer gameLoop = new AnimationTimer() {

    @Override
    public void handle(long now) {

        if (lastFrameTime[0] < 0) {
            lastFrameTime[0] = now;
            return;
        }
        double dt = (now - lastFrameTime[0]) / 1_000_000_000.0; //Deltatime creo
        lastFrameTime[0] = now;
        hook.update(now);
        fishManager.update(
        hook.getHookX(),
        hook.getHookY(),
        dt//??? 2
        );
         for (Fish fish : fishManager.getFishList()) {

    if (!fish.isCaptured()) {

        if (fish.isTouching( //Bloque de captura
                hook.getHookX(),
                hook.getHookY())) {

            fish.capture();
            addMoney(fish.getValue());
            moneyText.setText("Dinero: $" + getPlayerMoney());
            hook.setState(HookState.RAISING);

            break;
        }
    }
}
    }
    };
        gameLoop.start();
    }
    //un Update para la plata
    public int getPlayerMoney() {
    return playerMoney;
    }

    public void addMoney(int amount) {
    playerMoney += amount;
    }

    public boolean spendMoney(int amount) {
    if (playerMoney >= amount) {
        playerMoney -= amount;
        return true;
    }

    return false;
}
}