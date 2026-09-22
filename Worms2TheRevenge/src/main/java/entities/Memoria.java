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
}
