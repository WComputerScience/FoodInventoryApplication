package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Fruit;
import com.fridgemap.fridge.map.entity.Shelf;

import java.util.List;

public interface ShelfService {
    List<Shelf> getShelf();
    void save(Shelf shelf);
    Shelf getShelfByName(String name);
    void deleteShelfById(Long id);
    void decrementShelf(Shelf shelfItem);
    void incrementShelf(Shelf shelfItem, int count);
}
