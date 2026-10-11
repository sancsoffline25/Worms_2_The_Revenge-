/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sounds;

/**
 *
 * @author Santiago Guinel
 */
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuMusic{

    //reproductor unico para todo el menu
    private static MediaPlayer reproductor;

    //reproducimoss la musica del menu
    public static void reproducir() {

        //creamos el reproductor una vez
        if (reproductor == null) {

            Media musica = new Media(MenuMusic.class.getResource("/Assets/Musica/extendMainMenuSong.mp3").toExternalForm());
            reproductor = new MediaPlayer(musica);
            reproductor.setVolume(0.3);
            reproductor.setCycleCount(MediaPlayer.INDEFINITE);
        }

        //si ya esta sonando, no la reiniciamos porque no hace falta
        if (reproductor.getStatus() != MediaPlayer.Status.PLAYING) {
            reproductor.play();
        }
    }

    //detenemos la musica del menu
    public static void detener() {
        if (reproductor != null) {
            reproductor.stop();
        }
    }
}