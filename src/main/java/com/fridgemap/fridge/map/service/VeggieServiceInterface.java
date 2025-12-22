package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Cheese;
import com.fridgemap.fridge.map.entity.Vegetable;

import java.util.List;

public interface VeggieServiceInterface {
    List<Vegetable> getVegetable();
    void save(Vegetable vegetable);
    Vegetable getVegetableByName(String name);
    void deleteVegetableById(Long id);
    void decrementVegetable(Vegetable vegetableItem);
    void incrementVegetable(Vegetable vegetableItem, int change);

}
