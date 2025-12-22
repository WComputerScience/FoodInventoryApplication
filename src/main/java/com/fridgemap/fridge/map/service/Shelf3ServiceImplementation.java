package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Shelf2;
import com.fridgemap.fridge.map.entity.Shelf3;
import com.fridgemap.fridge.map.repository.Shelf3Repository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Shelf3ServiceImplementation implements Shelf3Service {

    @Autowired
    private Shelf3Repository shelf3Repository;

    @Override
    public List<Shelf3> getShelf3() {
        return shelf3Repository.findAll();
    }

    @Override
    public void save(Shelf3 shelf3) {
        shelf3Repository.save(shelf3);
    }

    @Override
    public Shelf3 getShelf3ByName(String name) {
        List<Shelf3> optshelf3 = shelf3Repository.findShelf3ByType(name);
        Shelf3 shelf3  = null;
        if(!optshelf3.isEmpty()){
            shelf3 = optshelf3.get(0);
        }
        else {
            throw new RuntimeException("Shelf2 not found.");
        }
        return shelf3;
    }

    @Transactional
    @Override
    public void deleteShelf3ById(Long id) {
        shelf3Repository.deleteShelf3ById(id);
    }

    @Transactional
    @Override
    public void decrementShelf3(Shelf3 shelf3Item) {
        shelf3Item.setQuantity(shelf3Item.getQuantity() - 1);
    }

    @Transactional
    @Override
    public void incrementShelf3(Shelf3 shelf3Item, int change) {
        shelf3Item.setQuantity(shelf3Item.getQuantity() + change);
        shelf3Repository.save(shelf3Item);
    }
}
