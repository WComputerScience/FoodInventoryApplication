package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Fruit;
import com.fridgemap.fridge.map.entity.Vegetable;
import com.fridgemap.fridge.map.repository.FruitRepository;
import com.fridgemap.fridge.map.repository.VegetableRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImplementation implements FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    @Override
    public List<Fruit> getFruit() {
        return fruitRepository.findAll();
    }

    @Override
    public void save(Fruit fruit) {
        fruitRepository.save(fruit);
    }

    @Override
    public Fruit getFruitByName(String name) {
        List<Fruit> optfruit = fruitRepository.findFruitByType(name);
        Fruit fruit  = null;
        if(!optfruit.isEmpty()){
            fruit = optfruit.get(0);
        }
        else {
            throw new RuntimeException("Vegetable not found.");
        }
        return fruit;
    }

    @Transactional
    @Override
    public void deleteFruitById(Long id) {
        fruitRepository.deleteFruitById(id);
    }

    @Transactional
    @Override
    public void decrementFruit(Fruit fruitItem) {
        fruitItem.setQuantity(fruitItem.getQuantity() - 1);
    }

    @Transactional
    @Override
    public void incrementFruit(Fruit fruitItem, int count) {
        fruitItem.setQuantity(fruitItem.getQuantity() + count);
        fruitRepository.save(fruitItem);
    }
}
