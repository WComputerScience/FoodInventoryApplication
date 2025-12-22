package com.fridgemap.fridge.map.repository;

import com.fridgemap.fridge.map.entity.Fruit;
import com.fridgemap.fridge.map.entity.Shelf2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Shelf2Repository extends JpaRepository<Shelf2, Long> {
    List<Shelf2> findShelf2ByType(String type);
    void deleteShelf2ByType(String type);

    void deleteShelf2ById(Long id);
}
