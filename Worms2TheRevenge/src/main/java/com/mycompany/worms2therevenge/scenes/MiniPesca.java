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
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.ResolutionManager;
import entities.FishManager;
import entities.Hook;
import entities.Memoria;
import entities.Player;
import ui.ButtonCreator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.text.Font.font;

public class MiniPesca{
    Font botonFont = Font.loadFont(getClass().getResourceAsStream("/Assets/Fonts/VT323-Regular.ttf"), 28);
    
    //Tamaño de la ventana
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    //Punto donde está la punta de la caña
    private final double ORIGIN_X = 400;
    private final double ORIGIN_Y = 60;
    
    //El dinero ahora se controla directamente desde Memoria.jaba
    
    //Objetos gráficos
    private Pane gamePane;
    
    private GamePlayBase gamePlayBase;
    public MiniPesca(GamePlayBase gamePlayBase){
    this.gamePlayBase = gamePlayBase;
    }
    //Carnada func
    private void intentarLanzar(Hook hook, Text baitText){
    if (hook.getState() != HookState.SWINGING){
        return;
    }
    if (!Memoria.consumirCarnada()){
        baitText.setText("Carnada: 0 - ¡Necesitas más!");
        return;
    }
    baitText.setText("Carnada: " + Memoria.getCarnada());
    hook.startLowering();
    }
    public void start(Stage stage, Player jugador){
       
        gamePane = new Pane();
        
       //No hay mas pez
        gamePane.setPrefSize(WIDTH, HEIGHT);
        //Fondo con IMAGEN!
        Image backgroundImage = new Image(
        getClass().getResource("/Assets/Backgrounds/MiniPesca/background2.png").toExternalForm()
        );
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(WIDTH);
        backgroundView.setFitHeight(HEIGHT);
        backgroundView.setPreserveRatio(false);

        gamePane.getChildren().add(backgroundView);
        
        //Display de dinero
        Text moneyText = new Text();
        moneyText.setText("Dinero: $" + Memoria.getPlayerMoney());
        moneyText.setX(20);
        moneyText.setY(30);
        moneyText.setStyle("-fx-font-size: 20px;");
        gamePane.getChildren().add(moneyText);
        //Display de carnada
        Text baitText = new Text();
        baitText.setX(20);
        baitText.setY(60);
        baitText.setStyle("-fx-font-size: 20px;");
        baitText.setText("Carnada: " + Memoria.getCarnada());
        gamePane.getChildren().add(baitText);
        
        
        //Referencia a Hook.java para los valores del anzuelo
        Hook hook = new Hook(ORIGIN_X, ORIGIN_Y,HEIGHT - 50);
        
        gamePane.getChildren().addAll(
                hook.getLine(),
                hook.getCircle()
        );
        
        FishManager fishManager = new FishManager(WIDTH);
        //Referencia a FishManager para hacer las filas
        fishManager.createRow(220, 8, 105, true, FishType.BASIC,0);

        fishManager.createRow(280, 4, 210, false, FishType.MID, 8);

        fishManager.createRow(340, 8, 105, true, FishType.BASIC,12);
        
        //fishManager.createRow(340, 4, 210, false, FishType.BASIC);
        
        fishManager.createRow(360, 8, 30, false, FishType.SWARM,20);
        fishManager.createRow(380, 8, 30, true, FishType.SWARM,28);
        
        fishManager.createRow(420, 3, 315, false, FishType.MID,36);
        
        fishManager.createRow(480, 1, 105, true, FishType.BIG,40);
        
        //Las variables son(Profundidad, cantidad, separacion,dirccion, typo)

        // Añadir los peces al gamePane
        for (Fish fish : fishManager.getFishList()) {
        gamePane.getChildren().add(fish.getSprite());
}   
        //Tienda maxxing
        //Tienda fondo
        Pane shopPane = new Pane();
        shopPane.setPrefSize(WIDTH, HEIGHT);
        Image shopBackgroundImage = new Image(
        getClass().getResource("/Assets/Backgrounds/MiniPescaTienda/TiendaBackground.png").toExternalForm()
        );
        ImageView shopBackgroundView = new ImageView(shopBackgroundImage);
        shopBackgroundView.setFitWidth(WIDTH);
        shopBackgroundView.setFitHeight(HEIGHT);
        shopBackgroundView.setPreserveRatio(false);
        shopBackgroundView.setMouseTransparent(true);
        shopPane.getChildren().add(shopBackgroundView);
        
        Text shopTitle = new Text("TIENDA");
        shopTitle.setX(350);
        shopTitle.setY(80);
        shopTitle.setStyle("-fx-font-size: 30px;");
        shopTitle.setFont(botonFont);
        
        shopPane.getChildren().add(shopTitle);
        
        Text shopMoneyText = new Text();
        shopMoneyText.setX(20);
        shopMoneyText.setY(30);
        shopMoneyText.setStyle("-fx-font-size: 20px;");
        shopMoneyText.setFont(botonFont);
        shopPane.getChildren().add(shopMoneyText);
        shopMoneyText.setText("Dinero: $" + Memoria.getPlayerMoney());
        
        //Dentro de la tienda
        //Boton de cerrar
        Button closeShopButton = new Button("Cerrar");
        closeShopButton.setLayoutX(700);
        closeShopButton.setLayoutY(100);
        closeShopButton.setFont(botonFont);
        shopPane.getChildren().add(closeShopButton);
        
        //Productos de la tienda
        ButtonCreator botonmaker = new ButtonCreator();
        // Reponer peces
        StackPane restoreFishButton =
                botonmaker.crearBotonTienda("Reponer peces - $10", botonFont);
        restoreFishButton.setLayoutX(272);
        restoreFishButton.setLayoutY(143);
        shopPane.getChildren().add(restoreFishButton);
        // Reintentos
        StackPane retryButton =
        botonmaker.crearBotonTienda("Compra reintento - $25", botonFont);
        retryButton.setLayoutX(272);
        retryButton.setLayoutY(213);
        shopPane.getChildren().add(retryButton);
        // Ataque
        StackPane attackButton =
        botonmaker.crearBotonTienda("Afilar anzuelo - $100", botonFont);
        attackButton.setLayoutX(272);
        attackButton.setLayoutY(283);
        shopPane.getChildren().add(attackButton);
        //Titulo
        Label titulo = new Label("Minijuego de Pesca");

        Button launchButton = new Button("Lanzar anzuelo");
        Button backButton = new Button("Volver");
        Button tiendaButton = new Button("Tienda");

        launchButton.setOnAction(e -> intentarLanzar(hook, baitText));
        //Botones y funciones 
        backButton.setOnAction(e -> {
        stage.close();
    });
        tiendaButton.setOnAction(e -> {
        shopMoneyText.setText("Dinero: $" + Memoria.getPlayerMoney());
        shopPane.setVisible(true);
    });
        closeShopButton.setOnAction(e -> {
    MiniPesca miniPesca = new MiniPesca(gamePlayBase);
    miniPesca.start(stage, jugador);
    shopPane.setVisible(false);
    });
        
    // Reponer peces
    restoreFishButton.setOnMouseClicked(e -> {
    int price = 10;

    if (Memoria.getPlayerMoney() >= price) {
        Memoria.addMoney(-price);
        Memoria.resetCapturedFish();

        shopMoneyText.setText(
            "Dinero: $" + Memoria.getPlayerMoney()
        );

        Memoria.resetFishSprites();
    }
});

   // Reintentos
   retryButton.setOnMouseClicked(e -> {
    int price = 25;
    int cantidad = 2;

    if (Memoria.getPlayerMoney() >= price) {
        if (Memoria.compraReintentos(jugador, cantidad)) {
            Memoria.addMoney(-price);

            shopMoneyText.setText(
                "Dinero: $" + Memoria.getPlayerMoney()
            );

            gamePlayBase.actualizarIntentos(
                jugador.getReintentos()
            );
        }
    }
});

   // Ataque
   attackButton.setOnMouseClicked(e -> {
    int price = 100;
    int cantidad = 25;

    if (Memoria.getPlayerMoney() >= price) {
        if (Memoria.compraAtaque(jugador, cantidad)) {
            Memoria.addMoney(-price);

            shopMoneyText.setText(
                "Dinero: $" + Memoria.getPlayerMoney()
            );
        }
    }
});
   gamePane.getChildren().add(shopPane);
   shopPane.setVisible(false);

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(launchButton, tiendaButton, backButton);

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
                intentarLanzar(hook, baitText);
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
            Memoria.captureFish(fish.getId());
            Memoria.addMoney(fish.getValue());
            moneyText.setText("Dinero: $" + Memoria.getPlayerMoney());
            hook.setState(HookState.RAISING);

            break;
        }
    }
}
    }
    };
        gameLoop.start();
    }
    //Aca habia un update, ya no
}
