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
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;


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
    
    //Boleano para los sonidos
    private boolean peleaActiva = true;
    
    private ArrayList<ImageView> manosActivas = new ArrayList<>();
    private ArrayList<TranslateTransition> ataquesActivos = new ArrayList<>();
    private ArrayList<AnimationTimer> colisionesActivas = new ArrayList<>();
    
    public void detenerSonidos(){
        peleaActiva = false;
        
        for(TranslateTransition ataque : ataquesActivos){
            ataque.stop();
        }
        
        for(AnimationTimer colision : colisionesActivas){
            colision.stop();
        }
        
        for(ImageView mano : manosActivas){
            if(mano.getParent() instanceof StackPane){
                ((StackPane) mano.getParent()).getChildren().remove(mano);
            }
        }
        
        ataquesActivos.clear();
        colisionesActivas.clear();
        manosActivas.clear();
    }
        
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

        if(!peleaActiva){
            return;
        }

        ImageView mano = new ImageView(manoDerHorizontal);

        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        //Sonido de aparición de la mano
        if(peleaActiva){
            viejoSonidos.throwHandEffect();
        }
        

        //Posiciones
        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );
        
        escena.getChildren().add(mano);
        manosActivas.add(mano);
        
        //Animacion
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );
        
        ataquesActivos.add(ataque);

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        //Detección de colisión
        AnimationTimer colision = new AnimationTimer() {
            
            @Override
            public void handle(long ahora) {

                if(!peleaActiva){
                    stop();
                    return;
                }

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    if(peleaActiva){
                        playerSonidos.damageSoundEffect();
                    }

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };
        
        colisionesActivas.add(colision);

        //Cuando termina el ataque
        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
            
            ataquesActivos.remove(ataque);
            colisionesActivas.remove(colision);
            manosActivas.remove(mano);
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

        if(!peleaActiva){
            return;
        }

        ImageView mano = new ImageView(manoIzqHorizontal);

        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        if(peleaActiva){
            viejoSonidos.throwHandEffect();
        }

        //Posiciones
        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        escena.getChildren().add(mano);
        manosActivas.add(mano);
        
        //Animacion
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );
        
        ataquesActivos.add(ataque);

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        //Colision
        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if(!peleaActiva){
                    stop();
                    return;
                }

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    if(peleaActiva){
                        playerSonidos.damageSoundEffect();
                    }

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };
        
        colisionesActivas.add(colision);

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
            
            ataquesActivos.remove(ataque);
            colisionesActivas.remove(colision);
            manosActivas.remove(mano);
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

        if(!peleaActiva){
            return;
        }

        ImageView mano = new ImageView(manoDerVertical);

        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        if(peleaActiva){
            viejoSonidos.throwHandEffect();
        }

        // Posiciones
        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );

        escena.getChildren().add(mano);
        manosActivas.add(mano);
        
        // Animación
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );
        
        ataquesActivos.add(ataque);

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        // Colisión
        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if(!peleaActiva){
                    stop();
                    return;
                }

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    if(peleaActiva){
                        playerSonidos.damageSoundEffect();
                    }

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };
        
        colisionesActivas.add(colision);

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
            
            ataquesActivos.remove(ataque);
            colisionesActivas.remove(colision);
            manosActivas.remove(mano);
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

        if(!peleaActiva){
            return;
        }

        ImageView mano = new ImageView(manoIzquierda);

        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        if(peleaActiva){
            viejoSonidos.throwHandEffect();
        }

        // Posiciones
        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );
        
        escena.getChildren().add(mano);
        manosActivas.add(mano);
        
        // Animación
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );
        
        ataquesActivos.add(ataque);
        
        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        // Colisión
        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if(!peleaActiva){
                    stop();
                    return;
                }

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    if(peleaActiva){
                        playerSonidos.damageSoundEffect();
                    }

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };
        
        colisionesActivas.add(colision);

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
            
            ataquesActivos.remove(ataque);
            colisionesActivas.remove(colision);
            manosActivas.remove(mano);
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

        if(!peleaActiva){
            return;
        }

        ImageView mano = new ImageView(manoDerDiagonalTop);

        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        if(peleaActiva){
            viejoSonidos.throwHandEffect();
        }

        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );
        
        escena.getChildren().add(mano);
        manosActivas.add(mano);
        
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );
        
        ataquesActivos.add(ataque);

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if(!peleaActiva){
                    stop();
                    return;
                }

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    if(peleaActiva){
                        playerSonidos.damageSoundEffect();
                    }

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };
        
        colisionesActivas.add(colision);

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
            
            ataquesActivos.remove(ataque);
            colisionesActivas.remove(colision);
            manosActivas.remove(mano);
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

        if(!peleaActiva){
            return;
        }

        ImageView mano = new ImageView(manoIzqDiagonalTop);

        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        if(peleaActiva){
            viejoSonidos.throwHandEffect();
        }

        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );
        
        escena.getChildren().add(mano);
        manosActivas.add(mano);
        
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );
        
        ataquesActivos.add(ataque);

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if(!peleaActiva){
                    stop();
                    return;
                }

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    if(peleaActiva){
                        playerSonidos.damageSoundEffect();
                    }

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };
        
        colisionesActivas.add(colision);

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
            
            ataquesActivos.remove(ataque);
            colisionesActivas.remove(colision);
            manosActivas.remove(mano);
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

        if(!peleaActiva){
            return;
        }

        ImageView mano = new ImageView(manoDerDiagonalDown);

        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        if(peleaActiva){
            viejoSonidos.throwHandEffect();
        }

        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );
        
        escena.getChildren().add(mano);
        manosActivas.add(mano);
        
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );
        
        ataquesActivos.add(ataque);

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if(!peleaActiva){
                    stop();
                    return;
                }

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    if(peleaActiva){
                        playerSonidos.damageSoundEffect();
                    }

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );

                    stop();
                }
            }
        };
        
        colisionesActivas.add(colision);

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
            
            ataquesActivos.remove(ataque);
            colisionesActivas.remove(colision);
            manosActivas.remove(mano);
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

        if(!peleaActiva){
            return;
        }

        ImageView mano = new ImageView(manoIzqDiagonalDown);

        StackPane.setAlignment(mano, javafx.geometry.Pos.TOP_LEFT);

        if(peleaActiva){
            viejoSonidos.throwHandEffect();
        }

        Point2D inicio = escena.sceneToLocal(
                spawnInicio.localToScene(0, 0)
        );

        Point2D finalPos = escena.sceneToLocal(
                spawnFinal.localToScene(0, 0)
        );
        
        escena.getChildren().add(mano);
        manosActivas.add(mano);
        
        TranslateTransition ataque = new TranslateTransition(
                Duration.seconds(duracionAtaque),
                mano
        );
        
        ataquesActivos.add(ataque);

        ataque.setFromX(inicio.getX());
        ataque.setToX(finalPos.getX());

        ataque.setFromY(inicio.getY());
        ataque.setToY(finalPos.getY());

        AnimationTimer colision = new AnimationTimer() {

            @Override
            public void handle(long ahora) {

                if(!peleaActiva){
                    stop();
                    return;
                }

                if (mano.getBoundsInParent().intersects(
                        playerView.getBoundsInParent()
                )) {

                    jugador.recibirDanio(25);

                    statusAnimations.mostrarDanio(
                            jugador,
                            playerView
                    );

                    if(peleaActiva){
                        playerSonidos.damageSoundEffect();
                    }

                    statusAnimations.actualizarBarra(
                            jugador,
                            playerBar,
                            playerBarView
                    );
                    stop();
                }
            }
        };
        
        colisionesActivas.add(colision);

        ataque.setOnFinished(e -> {

            escena.getChildren().remove(mano);

            colision.stop();
            
            ataquesActivos.remove(ataque);
            colisionesActivas.remove(colision);
            manosActivas.remove(mano);
        });

        colision.start();
        ataque.play();
    }
}
    
