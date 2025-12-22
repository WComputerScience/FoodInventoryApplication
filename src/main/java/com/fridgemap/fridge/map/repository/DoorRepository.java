package com.fridgemap.fridge.map.repository;

import com.fridgemap.fridge.map.entity.Cheese;
import com.fridgemap.fridge.map.entity.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {
    List<Door> findDoorByType(String type);

    void deleteDoorByType(String type);

    void deleteDoorById(Long id);
}
