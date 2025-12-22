package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Fruit;
import com.fridgemap.fridge.map.entity.Shelf;
import com.fridgemap.fridge.map.entity.Shelf2;

import java.util.List;

public interface Shelf2Service {
    List<Shelf2> getShelf2();
    void save(Shelf2 shelf2);
    Shelf2 getShelf2ByName(String name);
    void deleteShelf2ById(Long id);
    void decrementShelf2(Shelf2 shelf2Item);
    void incrementShelf2(Shelf2 shelf2Item, int count);

}
