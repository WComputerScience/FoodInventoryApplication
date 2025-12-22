package com.fridgemap.fridge.map.manualloaddata;

import com.fridgemap.fridge.map.entity.*;
import com.fridgemap.fridge.map.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoadData implements CommandLineRunner {

    private final CheeseRepository cheeseRepository;

    private final VegetableRepository vegetableRepository;

    private final FruitRepository fruitRepository;

    private final ShelfRepository shelfRepository;

    private final Shelf2Repository shelf2Repository;

    private final Shelf3Repository shelf3Repository;

    private final DoorRepository doorRepository;



    @Autowired
    public LoadData(CheeseRepository cheeseRepository, VegetableRepository vegetableRepository, FruitRepository fruitRepository,
                    Shelf2Repository shelf2repository, ShelfRepository shelfRepository, Shelf3Repository shelf3Repository,
                    DoorRepository doorRepository) {
        this.cheeseRepository = cheeseRepository;
        this.vegetableRepository = vegetableRepository;
        this.fruitRepository = fruitRepository;
        this.shelfRepository = shelfRepository;
        this.shelf2Repository = shelf2repository;
        this.shelf3Repository = shelf3Repository;
        this.doorRepository = doorRepository;

    }





    @Override
    public void run(String... args) throws Exception {
        System.out.println("RUNNING COMMAND LINE RUNNER!!!!");
        //Check if the data is already there.
        if(cheeseRepository.findAll().isEmpty()) {
            cheeseRepository.save(new Cheese("Cheddar", 1));
            cheeseRepository.save(new Cheese("Swiss", 2));
            cheeseRepository.save(new Cheese("Havarti", 3));
            System.out.println("Helloworld saved a cheese!!!!!!");
            cheeseRepository.save(new Cheese("Goat Cheese", 3));

        }
        else {
            System.out.println("The cheese repository was full.");
        }

        System.out.println("PROCESS FINISHED!!!!");
        List<Cheese> cheeses = cheeseRepository.findAll();
        for(Cheese cheese : cheeses) {
            System.out.println(cheese.getType());
        }

        if(vegetableRepository.findAll().isEmpty()) {
            vegetableRepository.save(new Vegetable("Carrots Bunch", 1));
            vegetableRepository.save(new Vegetable("Endive Lettuce", 2));
            vegetableRepository.save(new Vegetable("Arugula", 3));
            vegetableRepository.save(new Vegetable("Beets", 6));
            System.out.println("Vegetable saved a vegetable!!!!");
        }
        else {
            System.out.println("The vegetable repository was full.");
        }


        if(fruitRepository.findAll().isEmpty()) {
            fruitRepository.save(new Fruit("Lemons", 3));
            fruitRepository.save(new Fruit("Apples", 9));
            System.out.println("Fruits repository saved an item!!!!");
        }
        else {
            System.out.println("The fruits repository was full.");
        }

        if(shelfRepository.findAll().isEmpty()) {
            shelfRepository.save(new Shelf("Orange Juice", 8));
            shelfRepository.save(new Shelf("Pizza", 1));
            System.out.println("Shelf repository saved an item!!!!");
        }
        else {
            System.out.println("The shelf 1 repository was full.");
        }

        if(shelf2Repository.findAll().isEmpty()) {
            shelf2Repository.save(new Shelf2("Leftovers", 3));
            shelf2Repository.save(new Shelf2("Yogurt", 9));
            System.out.println("The shelf 2repository saved an item!!!!");
        }
        else {
            System.out.println("The shelf 2 repository was full.");
        }

        if(shelf3Repository.findAll().isEmpty()) {
            shelf3Repository.save(new Shelf3("Baked Potato", 3));
            shelf3Repository.save(new Shelf3("French Fries", 9));
            System.out.println("The shelf 2repository saved an item!!!!");
        }
        else {
            System.out.println("The shelf 2 repository was full.");
        }

        if(doorRepository.findAll().isEmpty()) {
            doorRepository.save(new Door("Salad", 2));
            doorRepository.save(new Door("Grape Juice", 1));
            System.out.println("The shelf door saved an item!!!!");
        }
        else {
            System.out.println("The door repository was full.");
        }

    }
}
