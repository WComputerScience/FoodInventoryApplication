package com.fridgemap.fridge.map.repository;

import com.fridgemap.fridge.map.entity.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
public interface CheeseRepository extends JpaRepository<Cheese, Long> {
    List<Cheese> findCheeseByType(String type);

    void deleteCheeseByType(String type);

    void deleteCheeseById(Long id);

}
