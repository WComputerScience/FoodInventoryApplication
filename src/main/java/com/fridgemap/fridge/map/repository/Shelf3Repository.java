package com.fridgemap.fridge.map.repository;

import com.fridgemap.fridge.map.entity.Shelf2;
import com.fridgemap.fridge.map.entity.Shelf3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Shelf3Repository extends JpaRepository<Shelf3, Long> {
    List<Shelf3> findShelf3ByType(String type);
    void deleteShelf3ByType(String type);

    void deleteShelf3ById(Long id);
}
