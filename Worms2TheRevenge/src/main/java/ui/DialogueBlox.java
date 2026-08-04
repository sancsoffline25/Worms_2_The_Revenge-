/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Santiago Guinel
 */

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class DialogueBlox extends VBox{
    
    private final Label nombre = new Label();
    private final Label texto = new Label();
    
    public DialogueBlox(){
    
        setSpacing(8);
        setAlignment(Pos.TOP_LEFT);
        
        setStyle(
                "-fx-background-color: rgba(0,0,0,0.85);" +
                "-fx-border-color: white;" +
                "-fx-border-width: 2;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-padding: 16;" 
        );
        
        setMaxWidth(500);
        
        nombre.setFont(Font.font("Console", 20));
        nombre.setTextFill(Color.YELLOW);
        
        texto.setFont(Font.font("Console", 16));
        texto.setTextFill(Color.WHITE);
        texto.setWrapText(true);
        
        getChildren().addAll(
                nombre,
                texto
        );
        
        setVisible(false);
    }
    
    public void mostrar(String quien, String mensaje){
        nombre.setText(quien);
        texto.setText(mensaje);
        setVisible(true);
    }
    
    public void ocultar(){
    setVisible(false);
    
    }
    
}
