/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package entities;

/**
 *
 * @author laros
 */

    public enum FishType{

    BASIC(
            "Pez feo",
            10,
            1
    );

    private final String name;
    private final int value;
    private final int difficulty;

    FishType(String name, int value, int difficulty) {
        this.name = name;
        this.value = value;
        this.difficulty = difficulty;
    }
    

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getDifficulty() {
        return difficulty;
    }
}