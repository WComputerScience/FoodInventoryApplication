package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Cheese;
import com.fridgemap.fridge.map.entity.Door;
import com.fridgemap.fridge.map.entity.Fruit;

import java.util.List;

public interface DoorService {
    List<Door> getDoor();
    void save(Door door);
    void deleteDoorById(Long id);
    void decrementDoor(Door doorItem);
    void incrementDoor(Door doorItem, int change);
}
