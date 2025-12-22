package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Fruit;
import com.fridgemap.fridge.map.entity.Vegetable;

import java.util.List;

public interface FruitService {
    List<Fruit> getFruit();
    void save(Fruit fruit);
    Fruit getFruitByName(String name);
    void deleteFruitById(Long id);
    void decrementFruit(Fruit fruitItem);
    void incrementFruit(Fruit fruitItem, int count);
}
