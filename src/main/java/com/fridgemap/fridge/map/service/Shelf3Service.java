package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Shelf2;
import com.fridgemap.fridge.map.entity.Shelf3;

import java.util.List;

public interface Shelf3Service {
    List<Shelf3> getShelf3();
    void save(Shelf3 shelf3);
    Shelf3 getShelf3ByName(String name);
    void deleteShelf3ById(Long id);
    void decrementShelf3(Shelf3 shelf3Item);
    void incrementShelf3(Shelf3 shelf3Item, int count);

}
