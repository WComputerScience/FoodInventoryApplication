package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Shelf;
import com.fridgemap.fridge.map.entity.Shelf2;
import com.fridgemap.fridge.map.repository.Shelf2Repository;
import com.fridgemap.fridge.map.repository.ShelfRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Shelf2ServiceImplementation implements Shelf2Service {

    @Autowired
    private Shelf2Repository shelf2Repository;

    @Override
    public List<Shelf2> getShelf2() {
        return shelf2Repository.findAll();
    }

    @Override
    public void save(Shelf2 shelf2) {
        shelf2Repository.save(shelf2);
    }

    @Override
    public Shelf2 getShelf2ByName(String name) {
        List<Shelf2> optshelf2 = shelf2Repository.findShelf2ByType(name);
        Shelf2 shelf2  = null;
        if(!optshelf2.isEmpty()){
            shelf2 = optshelf2.get(0);
        }
        else {
            throw new RuntimeException("Shelf2 not found.");
        }
        return shelf2;
    }

    @Transactional
    @Override
    public void deleteShelf2ById(Long id) {
        shelf2Repository.deleteShelf2ById(id);
    }

    @Transactional
    @Override
    public void decrementShelf2(Shelf2 shelf2Item) {
        shelf2Item.setQuantity(shelf2Item.getQuantity() - 1);
    }

    @Transactional
    @Override
    public void incrementShelf2(Shelf2 shelf2Item, int change) {
        shelf2Item.setQuantity(shelf2Item.getQuantity() + change);
        shelf2Repository.save(shelf2Item);
    }
}
