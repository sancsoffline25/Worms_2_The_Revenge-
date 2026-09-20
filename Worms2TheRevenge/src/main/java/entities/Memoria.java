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

public class Memoria {

    private static final Set<Integer> capturedFish = new HashSet<>();

    public static void captureFish(int fishId) {
        capturedFish.add(fishId);
    }

    public static boolean isCaptured(int fishId) {
        return capturedFish.contains(fishId);
    }
}
