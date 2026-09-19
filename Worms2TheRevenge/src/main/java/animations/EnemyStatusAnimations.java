 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package animations; 
/**
 *
 * @author Santiago Guinel
 */
import entities.Enemy;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class EnemyStatusAnimations {
    public void mostrarDanio(Enemy viejo, ImageView viejoView){
        
        //Mostramos el sprite de daño
        viejoView.setImage(viejo.getDamageSprite());
        
        //Temblor
        TranslateTransition temblor = new TranslateTransition(
                Duration.seconds(0.08),
                viejoView
        );
        
        temblor.setByX(8);
        temblor.setCycleCount(6);
        temblor.setAutoReverse(true);
        temblor.play();
        
        //Esperamos 1 segundo pa devolver el sprite normal
        PauseTransition pausa = new PauseTransition(Duration.seconds(1.0));
        
        pausa.setOnFinished(e ->{
            //Volvemos al sprite normal
            viejoView.setImage(viejo.getIdleSprite());
        });
        
        pausa.play();
    }
}