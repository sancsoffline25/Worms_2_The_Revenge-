/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package animations;

import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;


//entidades incorporadas
import entities.Player;
import sounds.oldmansounds;
import sounds.playersounds;
import ui.PlayerHealthBar;

/**
 *
 * @author Santiago Guinel
 */
public class EnemyAttackAnimations {
    
    //=== Atributos de la clase ===
    
    //Sprites Manos
    private Image manoIzquierda = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/leftHand.png"));
    private Image manoDerecha = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/rightHand.png"));
    private Image manoIzqHorizontal = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/horizontalLH.png"));
    private Image manoDerHorizontal = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/horizontalRH.png"));
    private Image manoIzqVertical = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/verticalDownLH.png"));
    private Image manoDerVertical = new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/verticalDownRH.png"));
    private Image manoDerDiagonalTop= new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/DiagonalTopRH.png"));
    private Image manoDerDiagonalDown= new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/DiagonalDownRH.png"));
    private Image manoIzqDiagonalTop= new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/DiagonalTopLH.png"));
    private Image manoIzqDiagonalDown= new Image(getClass().getResourceAsStream("/Assets/Sprites/bossfight/hands/DiagonalDownLH.png"));
        
    //=== Animaciones de Ataque ===
    
    //estados del jugador
    PlayerStatusAnimations statusAnimations = new PlayerStatusAnimations();
    PlayerHealthBar playerBar = new PlayerHealthBar();
    
    //Efectos de sonido
    oldmansounds viejoSonidos = new oldmansounds();
    playersounds playerSonidos = new playersounds();
    
    //ATAQUES HORIZONTALES

    //-- Ataque horizontal derecha
    public void ataqueHorizontalDer(
            StackPane escena,
            Circle spawnInicio,
            Circle spawnFinal,
            double duracionAtaque,
            ImageView playerView,
            Player jugador,
            PlayerHealthBar playerBar,
            ImageView playerBarView
    ) {

        ImageView mano = new ImageView(manoDerHorizontal);

        escena.getChildren().add(mano);
        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        //Sonido de aparición de la mano
        viejoSonidos.throwHandEffect();

        //Posiciones
        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        //Animacion
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        //Detección de colisión
        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    playerSonidos.damageSoundEffect();

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };

        //Cuando termina el ataque
        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
        });

        colision.start();
        ataque.play();
    }

    //-- Ataque horizontal izquierda
    public void ataqueHorizontalIzq(
            StackPane escena,
            Circle spawnInicio,
            Circle spawnFinal,
            double duracionAtaque,
            ImageView playerView,
            Player jugador,
            PlayerHealthBar playerBar,
            ImageView playerBarView
    ) {

        ImageView mano = new ImageView(manoIzqHorizontal);

        escena.getChildren().add(mano);
        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        viejoSonidos.throwHandEffect();

        //Posiciones
        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        //Animacion
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        //Colision
        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    playerSonidos.damageSoundEffect();

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
        });

        colision.start();
        ataque.play();
    }


    //ATAQUES VERTICALES

    //-- Ataque vertical desde arriba
    public void ataqueVerticalTop(
            StackPane escena,
            Circle spawnInicio,
            Circle spawnFinal,
            double duracionAtaque,
            ImageView playerView,
            Player jugador,
            PlayerHealthBar playerBar,
            ImageView playerBarView
    ) {

        ImageView mano = new ImageView(manoDerVertical);

        escena.getChildren().add(mano);
        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        viejoSonidos.throwHandEffect();

        // Posiciones
        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        // Animación
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        // Colisión
        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    playerSonidos.damageSoundEffect();

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    System.out.println(
                            "Vida restante: " + jugador.getVida()
                    );

                    stop();
                }
            }
        };

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
        });

        colision.start();
        ataque.play();
    }

    //-- Ataque vertical desde abajo
    public void ataqueVerticalDown(
            StackPane escena,
            Circle spawnInicio,
            Circle spawnFinal,
            double duracionAtaque,
            ImageView playerView,
            Player jugador,
            PlayerHealthBar playerBar,
            ImageView playerBarView
    ) {

        ImageView mano = new ImageView(manoIzquierda);

        escena.getChildren().add(mano);
        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        viejoSonidos.throwHandEffect();

        // Posiciones
        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        // Animación
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        // Colisión
        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    playerSonidos.damageSoundEffect();

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    System.out.println(
                            "Vida restante: " + jugador.getVida()
                    );

                    stop();
                }
            }
        };

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
        });

        colision.start();
        ataque.play();
    }

    //ATAQUES DIAGONALES

    //-- Ataque diagonal arriba derecha
    public void ataqueDiagonalTopDer(
            StackPane escena,
            Circle spawnInicio,
            Circle spawnFinal,
            double duracionAtaque,
            ImageView playerView,
            Player jugador,
            PlayerHealthBar playerBar,
            ImageView playerBarView
    ) {

        ImageView mano = new ImageView(manoDerDiagonalTop);

        escena.getChildren().add(mano);
        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        viejoSonidos.throwHandEffect();

        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    playerSonidos.damageSoundEffect();

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    System.out.println(
                            "Vida restante: " + jugador.getVida()
                    );

                    stop();
                }
            }
        };

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
        });

        colision.start();
        ataque.play();
    }

    //-- Ataque diagonal arriba izquierda
    public void ataqueDiagonalTopIzq(
            StackPane escena,
            Circle spawnInicio,
            Circle spawnFinal,
            double duracionAtaque,
            ImageView playerView,
            Player jugador,
            PlayerHealthBar playerBar,
            ImageView playerBarView
    ) {

        ImageView mano = new ImageView(manoIzqDiagonalTop);

        escena.getChildren().add(mano);
        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        viejoSonidos.throwHandEffect();

        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    playerSonidos.damageSoundEffect();

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    System.out.println(
                            "Vida restante: " + jugador.getVida()
                    );

                    stop();
                }
            }
        };

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
        });

        colision.start();
        ataque.play();
    }

    //-- Ataque diagonal abajo derecha
    public void ataqueDiagonalDownDer(
            StackPane escena,
            Circle spawnInicio,
            Circle spawnFinal,
            double duracionAtaque,
            ImageView playerView,
            Player jugador,
            PlayerHealthBar playerBar,
            ImageView playerBarView
    ) {

        ImageView mano = new ImageView(manoDerDiagonalDown);

        escena.getChildren().add(mano);
        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        viejoSonidos.throwHandEffect();

        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    playerSonidos.damageSoundEffect();

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    System.out.println(
                            "Vida restante: " + jugador.getVida()
                    );

                    stop();
                }
            }
        };
        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
        });

        colision.start();
        ataque.play();
    }

    //-- Ataque diagonal abajo izquierda
    public void ataqueDiagonalDownIzq(
            StackPane escena,
            Circle spawnInicio,
            Circle spawnFinal,
            double duracionAtaque,
            ImageView playerView,
            Player jugador,
            PlayerHealthBar playerBar,
            ImageView playerBarView
    ) {

        ImageView mano = new ImageView(manoIzqDiagonalDown);

        escena.getChildren().add(mano);
        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        viejoSonidos.throwHandEffect();

        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    playerSonidos.damageSoundEffect();

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );
                    System.out.println(
                            "Vida restante: " + jugador.getVida()
                    );
                    stop();
                }
            }
        };
        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
        });

        colision.start();
        ataque.play();
    }
}
    
