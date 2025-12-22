package com.fridgemap.fridge.map.service;

import com.fridgemap.fridge.map.entity.Cheese;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fridgemap.fridge.map.repository.CheeseRepository;

import java.util.List;

@Service
public class CheeseService implements CheeseServiceInterface{

    @Autowired
    private CheeseRepository cheeseRepository;

    @Override
    public List<Cheese> getCheese() {
        return cheeseRepository.findAll();
    }

    @Override
    public void save(Cheese cheese) {
        cheeseRepository.save(cheese);
    }

    @Override
    public Cheese getByName(String name) {
        List<Cheese> optcheese = cheeseRepository.findCheeseByType(name);
        Cheese cheese  = null;
        if(!optcheese.isEmpty()){
            cheese = optcheese.get(0);
        }
        else {
            throw new RuntimeException("Cheese not found.");
        }
        return cheese;
    }

    @Transactional
    @Override
    public void deleteCheeseByName(String cheesetype) {
        cheeseRepository.deleteCheeseByType(cheesetype);
    }

    @Transactional
    @Override
    public void deleteCheeseById(Long id) {
        cheeseRepository.deleteCheeseById(id);
    }

    @Transactional
    @Override
    public void decrementCheese(Cheese cheeseItem) {
        System.out.println("Decremented cheese by:" + (cheeseItem.getQuantity() - 1));
        cheeseItem.setQuantity(cheeseItem.getQuantity() - 1);
    }

    @Transactional
    @Override
    public void incrementCheese(Cheese cheeseItem, int change) {

        cheeseItem.setQuantity(cheeseItem.getQuantity() + change);
        cheeseRepository.save(cheeseItem);
    }


}
