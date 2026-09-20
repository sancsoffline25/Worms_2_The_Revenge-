/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author laros
 */
import java.util.ArrayList;
import java.util.List;

public class FishManager{
    private final List<Fish> fishList = new ArrayList<>();
    private final double screenWidth;

    public FishManager(double screenWidth){
        this.screenWidth = screenWidth;
    }
    public void addFish(Fish fish){
        fishList.add(fish);
    }
    public List<Fish> getFishList(){
        return fishList;
    }
    public void update(double hookX, double hookY, double dt){

        for (Fish fish : fishList){

            fish.update(hookX, hookY, dt);
        }}
    public void createRow(
        double y,
        int amount,
        double spacing,
        boolean movingRight,
        FishType type
) {
    for (int i = 0; i < amount; i++) {
        double x = 100 + (i * spacing);

        Fish fish = new Fish(x, y, type);
        fish.setMovingRight(movingRight);

        fishList.add(fish);
    }}}
