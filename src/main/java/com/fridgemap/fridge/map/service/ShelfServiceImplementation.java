package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Cheese;
import com.fridgemap.fridge.map.entity.Fruit;
import com.fridgemap.fridge.map.entity.Shelf;
import com.fridgemap.fridge.map.entity.Vegetable;
import com.fridgemap.fridge.map.repository.FruitRepository;
import com.fridgemap.fridge.map.repository.ShelfRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfServiceImplementation implements ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    @Override
    public List<Shelf> getShelf() {
        return shelfRepository.findAll();
    }

    @Override
    public void save(Shelf shelf) {
        shelfRepository.save(shelf);
    }

    @Override
    public Shelf getShelfByName(String name) {
        List<Shelf> optshelf = shelfRepository.findShelfByType(name);
        Shelf shelf  = null;
        if(!optshelf.isEmpty()){
            shelf = optshelf.get(0);
        }
        else {
            throw new RuntimeException("Shelf not found.");
        }
        return shelf;
    }

    @Transactional
    @Override
    public void deleteShelfById(Long id) {
        shelfRepository.deleteShelfById(id);
    }

    @Transactional
    @Override
    public void decrementShelf(Shelf shelfItem) {
        shelfItem.setQuantity(shelfItem.getQuantity() - 1);
    }

    @Transactional
    @Override
    public void incrementShelf(Shelf shelfItem, int change) {

        shelfItem.setQuantity(shelfItem.getQuantity() + change);
        shelfRepository.save(shelfItem);
    }
}
