package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Cheese;
import com.fridgemap.fridge.map.entity.Door;
import com.fridgemap.fridge.map.entity.Fruit;
import com.fridgemap.fridge.map.entity.Shelf3;
import com.fridgemap.fridge.map.repository.DoorRepository;
import com.fridgemap.fridge.map.repository.FruitRepository;
import com.fridgemap.fridge.map.repository.Shelf3Repository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoorServiceImplementation implements DoorService {

    @Autowired
    private DoorRepository doorRepository;

    @Override
    public List<Door> getDoor() {
        return doorRepository.findAll();
    }

    @Override
    public void save(Door door) {
        doorRepository.save(door);
    }

    @Transactional
    @Override
    public void deleteDoorById(Long id) {
        doorRepository.deleteDoorById(id);
    }

    @Transactional
    @Override
    public void decrementDoor(Door doorItem) {
        doorItem.setQuantity(doorItem.getQuantity() - 1);
    }

    @Transactional
    @Override
    public void incrementDoor(Door doorItem, int change) {
        doorItem.setQuantity(doorItem.getQuantity() + change);
        doorRepository.save(doorItem);
    }
}