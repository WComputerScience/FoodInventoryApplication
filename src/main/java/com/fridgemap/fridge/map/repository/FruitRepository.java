package com.fridgemap.fridge.map.repository;

import com.fridgemap.fridge.map.entity.Cheese;
import com.fridgemap.fridge.map.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    List<Fruit> findFruitByType(String type);
    void deleteFruitByType(String type);

    void deleteFruitById(Long id);
}
