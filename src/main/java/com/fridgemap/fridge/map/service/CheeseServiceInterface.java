package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Cheese;

import java.util.List;

public interface CheeseServiceInterface {
    List<Cheese> getCheese();
    void save(Cheese cheese);
    Cheese getByName(String name);
    void deleteCheeseByName(String name);
    void deleteCheeseById(Long id);
    void decrementCheese(Cheese cheeseItem);
    void incrementCheese(Cheese cheeseItem, int change);
}
