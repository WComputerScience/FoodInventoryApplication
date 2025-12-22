package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Cheese;
import com.fridgemap.fridge.map.entity.Vegetable;
import com.fridgemap.fridge.map.repository.VegetableRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeggieServiceImplementation implements VeggieServiceInterface {

    @Autowired
    private VegetableRepository vegetableRepository;

    @Override
    public List<Vegetable> getVegetable() {
        return vegetableRepository.findAll();
    }

    @Override
    public void save(Vegetable vegetable) {
        vegetableRepository.save(vegetable);
    }

    @Override
    public Vegetable getVegetableByName(String name) {
        List<Vegetable> optvegetable = vegetableRepository.findVegetableByType(name);
        Vegetable vegetable  = null;
        if(!optvegetable.isEmpty()){
            vegetable = optvegetable.get(0);
        }
        else {
            throw new RuntimeException("Vegetable not found.");
        }
        return vegetable;
    }

    @Transactional
    @Override
    public void deleteVegetableById(Long id) {
        vegetableRepository.deleteVegetableById(id);
    }

    @Transactional
    @Override
    public void decrementVegetable(Vegetable vegetableItem) {
        vegetableItem.setQuantity(vegetableItem.getQuantity() - 1);
    }

    @Transactional
    @Override
    public void incrementVegetable(Vegetable vegetableItem, int change) {
        vegetableItem.setQuantity(vegetableItem.getQuantity() + change);
        vegetableRepository.save(vegetableItem);
    }
}
