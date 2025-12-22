package com.fridgemap.fridge.map.repository;

import com.fridgemap.fridge.map.entity.Cheese;
import com.fridgemap.fridge.map.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
    List<Vegetable> findVegetableByType(String type);

    void deleteVegetableByType(String type);

    void deleteVegetableById(Long id);
}
