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
            10, //Tamaño//
            "/Assets/Sprites/peses/basic2.png"
    ),
    MID(
            "Pez mid",
            10,
            1,
            25,
            14,
            "/Assets/Sprites/peses/mid2.png"
    ),
    SWARM(
            "Cantidad",
            5,
            1,
            75,
            8,
            "/Assets/Sprites/peses/basic2.png"
    ),
    BIG(
            "Pez gordo",
            100,
            1,
            300,
            20,
            "/Assets/Sprites/peses/gordo2.png"
);

    private final String name;
    private final int value;
    private final int difficulty;
    private final double speed;
    private final double size;
    private final String spritePath;

    FishType(String name, int value, int difficulty, double speed, double size, String spritePath) {
        this.name = name;
        this.value = value;
        this.difficulty = difficulty;
        this.speed = speed;
        this.size = size;
        this.spritePath = spritePath;
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
     public String getSpritePath() {
        return spritePath;
    }
}