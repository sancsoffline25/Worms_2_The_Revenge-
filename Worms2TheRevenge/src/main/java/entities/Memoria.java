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
import entities.FishType;
import entities.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Random; //Nuevos imports!! 10 de Octubre

//Este script se puede usar para otros minijuegos
//Probando probando 123, santi al habla: voy a ocupar este script para que el dialogo se reproduzca solo 1 vez
//Lauti acá, te dije que este script lo hice para los 2, no me tenes que pedir permiso. cambio.
public class Memoria {
    
    //MINIPESCA
    //Guarda los peces capturados
    private static final Set<Integer> capturedFish = new HashSet<>();
    //Guarda el dinero
    private static int playerMoney = 0;
    //Seleccion de sprites de peces
    private static final Map<FishType, Integer> selectedFishSprites = new HashMap<>();
    private static final Random random = new Random();
    
    //Sacrale id a los fish
    public static void captureFish(int fishId){
        capturedFish.add(fishId);
    }
    //Asesinar a los fish ya capturados
    public static boolean isCaptured(int fishId){
        return capturedFish.contains(fishId);
    }
    //Guardar espacio en la memoria que indique cual es el sprite actual
    public static int getSelectedSpriteIndex(FishType type){
    if (!selectedFishSprites.containsKey(type)){
        int amount = type.getSpritePaths().length;
        int selected = random.nextInt(amount);
        selectedFishSprites.put(type, selected);
    }
    return selectedFishSprites.get(type);
    }
    //Lo que dice el nombre de la variable
    public static void resetFishSprites(){
    selectedFishSprites.clear();
    }
    //Money
    public static int getPlayerMoney(){
    return playerMoney;
    }
    //Mas money
    public static void addMoney(int amount){
    playerMoney += amount;
    }
    //Reespawn de peces para la tienda
    public static void resetCapturedFish(){
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
    
    //dar reintentos por compra(agrega los botones dps)
    public static boolean compraReintentos(Player player, int cantidad){

    if (player == null || cantidad <= 0){
        return false;
    }

    if (player.getReintentos() + cantidad > 12){
        return false;
    }

    player.recibirReintentos(cantidad);
    return true;
}
    
}
