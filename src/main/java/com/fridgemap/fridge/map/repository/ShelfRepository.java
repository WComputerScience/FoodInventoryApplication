package com.fridgemap.fridge.map.repository;

import com.fridgemap.fridge.map.entity.Fruit;
import com.fridgemap.fridge.map.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    List<Shelf> findShelfByType(String type);
    void deleteShelfByType(String type);

    void deleteShelfById(Long id);
}
