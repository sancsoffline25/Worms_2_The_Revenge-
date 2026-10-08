/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author laros
 */
import java.util.HashSet;
import java.util.Set;

//Este script se puede usar para otros minijuegos
//Probando probando 123, santi al habla: voy a ocupar este script para que el dialogo se reproduzca solo 1 vez
//Lauti acá, te dije que este script lo hice para los 2, no me tenes que pedir permiso, simio; cambio.
public class Memoria {
    
    //MiniPesca
    //Guarda los peces capturados
    private static final Set<Integer> capturedFish = new HashSet<>();
    //Guarda el dinero
    private static int playerMoney = 0;
    
    //Sacrale id a los fish
    public static void captureFish(int fishId) {
        capturedFish.add(fishId);
    }
    //Asesinar a los fish ya capturados
    public static boolean isCaptured(int fishId) {
        return capturedFish.contains(fishId);
    }
    //Money
    public static int getPlayerMoney() {
    return playerMoney;
    }
    //Mas money
    public static void addMoney(int amount) {
    playerMoney += amount;
    }
    //Reespawn de peces para la tienda
    public static void resetCapturedFish() {
    capturedFish.clear();

}
    
    //GAMEPLAYBASE
    //Dialogo booleano
    
    //Guarda si el dialogo inicial ya fue escuchado
    private static boolean dialogoInicialEscuchado = false;
    
    public static boolean isDialogoInicialEscuchado(){
        return dialogoInicialEscuchado;
    }
    
    public static void marcarEscuchado(){
        dialogoInicialEscuchado = true;
    }
    
    //MAINMENU
    //Musica booleano
    private static boolean MainMenuMusic = false;
    
    public static boolean isMusicEscuchada(){
        return MainMenuMusic;
    }
    
    public void marcarEscuchadaMusic(){
        MainMenuMusic = true;
    }
    
    public void desmarcarEscuchadaMusic(){
        MainMenuMusic = false;
    }
    
    
    
}
