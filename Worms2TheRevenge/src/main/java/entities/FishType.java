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
            5, //Valor
            1, //"Fuerza" nose si voy a terminar usando esto
            50, //Velocidad
            10 //Tamaño//
    ),
    MID(
            "Pez mid",
            10,
            1,
            25,
            14
    ),
    SWARM(
            "Cantidad",
            5,
            1,
            75,
            8
    ),
    BIG(
            "Pez gordo",
            100,
            1,
            300,
            20
);

    private final String name;
    private final int value;
    private final int difficulty;
    private final double speed;
    private final double size;

    FishType(String name, int value, int difficulty, double speed, double size) {
        this.name = name;
        this.value = value;
        this.difficulty = difficulty;
        this.speed = speed;
        this.size = size;
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
    public double getSpeed() {
        return speed;
    }

    public double getSize() {
        return size;
    }
}