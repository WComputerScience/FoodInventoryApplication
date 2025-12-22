package com.fridgemap.fridge.map.Controllers;

import com.fridgemap.fridge.map.entity.*;
import com.fridgemap.fridge.map.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private CheeseService cheeseService;



    @ModelAttribute("cheeses")
    public List<Cheese> getCheeses() {
        return cheeseService.getCheese();
    }

    @ModelAttribute("Cheese")
    public Cheese getCheese() {
        return new Cheese();
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("cheeses", cheeseService.getCheese());
        return "index";
    }


    @PostMapping("/cheese/{id}")
    public String deleteCheese(@PathVariable("id") Long id, Model model) {
        //Loop through the cheeses find the one that has that id decrement its
        //quantity by one.
        for(Cheese cheeseItem : cheeseService.getCheese()) {
            if(cheeseItem.getId().equals(id)) {
                if(cheeseItem.getQuantity() > 2 || cheeseItem.getQuantity() == 2) {
                    cheeseItem.setQuantity(cheeseItem.getQuantity() - 1);
                    cheeseService.save(cheeseItem);
                }
                else {
                    cheeseService.deleteCheeseById(cheeseItem.getId());
                }
            }
        }
        model.addAttribute("cheeses", cheeseService.getCheese());
        return "index";
    }

    @GetMapping("/add")
    public String addPage(Model model) {

        Cheese newcheese = new Cheese();
        model.addAttribute("cheese", newcheese);

        if(!model.containsAttribute("cheeses")) {
            model.addAttribute("cheeses", cheeseService.getCheese());
        }
        model.addAttribute("addedcheesetype", newcheese.getType());
        model.addAttribute("addedcheesequantity", newcheese.getQuantity());

        return "add";
    }

    @GetMapping("/return-home")
    public String returnHome() {
        return "index";
    }


    @GetMapping("/return-home2")
    public String returnHome2() {
        return "index";
    }

    //Do not make a new one. You must save the one from the model attribute.
    @PostMapping("/add")
    public String saveCheese(@Valid Cheese Cheese, BindingResult result, Model model) {
        model.addAttribute("errormsg", "You have encountered an error!");
        if (result.hasErrors()) {
            System.out.println("THERE WAS AN ERROR");
            System.out.println(result.getAllErrors());
            return "add";
        }
        if(Cheese.getQuantity() == null || Cheese.getQuantity() == 0) {
            Cheese.setQuantity(1);
        }
        //Check if the user has entered a duplicate.  If so, combine them.
        String checkName = Cheese.getType().strip().toLowerCase();

        Boolean found = false;
        //Loop thru the existing cheeses to check for a duplicate.
        for (Cheese cheeseItem : cheeseService.getCheese()){
            if(cheeseItem.getType().strip().toLowerCase().equals(checkName)) {
                found = true;
                cheeseService.incrementCheese(cheeseItem, Cheese.getQuantity());
            }
        }
        if(!found) {
            cheeseService.save(Cheese);
        }

        model.addAttribute("newcheese", Cheese);
        model.addAttribute("cheese", Cheese);
        System.out.println("SUCCESSFULLY ADDED.");
        return "added";
    }


    /// Vegetables section.

    @Autowired
    private VeggieServiceImplementation vegetableService;

    @ModelAttribute("vegetables")
    public List<Vegetable> getVegetables() {
        return vegetableService.getVegetable();
    }


    @ModelAttribute("Vegetable")
    public Vegetable getVegetable() {
        return new Vegetable();
    }

    @PostMapping("/vegetable/{id}")
    public String deleteVegetable(@PathVariable("id") Long id, Model model) {
        //Loop through the cheeses find the one that has that id decrement its
        //quantity by one.
        for(Vegetable vegetableItem : vegetableService.getVegetable()) {
            if(vegetableItem.getId().equals(id)) {
                if(vegetableItem.getQuantity() > 2 || vegetableItem.getQuantity() == 2) {
                    vegetableService.decrementVegetable(vegetableItem);
                    vegetableService.save(vegetableItem);
                }
                else {
                    vegetableService.deleteVegetableById(vegetableItem.getId());
                }
            }
        }
        model.addAttribute("vegetables", vegetableService.getVegetable());
        return "index";
    }

    @GetMapping("/addvegetable")
    public String addVegetablePage(Model model) {

        Vegetable newvegetable = new Vegetable();
        model.addAttribute("vegetable", newvegetable);

        if(!model.containsAttribute("vegetables")) {
            model.addAttribute("vegetables", vegetableService.getVegetable());
        }

        return "addvegetable.html";
    }

    //Do not make a new one. You must save the one from the model attribute.
    @PostMapping("/addvegetable")
    public String saveVegetable(@Valid Vegetable vegetable, BindingResult result, Model model) {
        model.addAttribute("errormsg", "You have encountered an error!");
        if (result.hasErrors()) {
            System.out.println("THERE WAS AN ERROR");
            System.out.println(result.getAllErrors());
            return "addvegetable";
        }
        if(vegetable.getQuantity() == null || vegetable.getQuantity() == 0) {
            vegetable.setQuantity(1);
        }
        //Check if the user has entered a duplicate.  If so, combine them.
        String checkName = vegetable.getType().strip().toLowerCase();

        Boolean found = false;
        //Loop thru the existing cheeses to check for a duplicate.
        for (Vegetable vegetableItem : vegetableService.getVegetable()){
            if(vegetableItem.getType().strip().toLowerCase().equals(checkName)) {
                found = true;
               vegetableService.incrementVegetable(vegetableItem, vegetable.getQuantity());
            }
        }
        if(!found) {
            vegetableService.save(vegetable);
        }

        model.addAttribute("newvegetable", vegetable);
        model.addAttribute("vegetable", vegetable);
        System.out.println("SUCCESSFULLY ADDED.");
        return "addedvegetable";
    }

    //Fruits.

    @Autowired
    private FruitServiceImplementation fruitService;

    @ModelAttribute("fruits")
    public List<Fruit> getFruit() {
        return fruitService.getFruit();
    }


    @PostMapping("/fruit/{id}")
    public String deleteFruit(@PathVariable("id") Long id, Model model) {
        //Loop through the cheeses find the one that has that id decrement its
        //quantity by one.
        for(Fruit fruitItem : fruitService.getFruit()) {
            if(fruitItem.getId().equals(id)) {
                if(fruitItem.getQuantity() > 2 || fruitItem.getQuantity() == 2) {
                    fruitService.decrementFruit(fruitItem);
                    fruitService.save(fruitItem);
                }
                else {
                    fruitService.deleteFruitById(fruitItem.getId());
                    //fruitService.save(fruitItem);
                }
            }
        }
        model.addAttribute("fruits", fruitService.getFruit());
        return "index";
    }

    @GetMapping("/addfruit")
    public String addFruitPage(Model model) {

        Fruit newfruit = new Fruit();
        model.addAttribute("fruit", newfruit);

        if(!model.containsAttribute("fruits")) {
            model.addAttribute("fruits", fruitService.getFruit());
        }

        return "addfruit";
    }

    //Do not make a new one. You must save the one from the model attribute.
    @PostMapping("/addfruit")
    public String saveFruit(@Valid Fruit fruit, BindingResult result, Model model) {
        model.addAttribute("errormsg", "You have encountered an error!");
        if (result.hasErrors()) {
            System.out.println("THERE WAS AN ERROR");
            System.out.println(result.getAllErrors());
            return "addfruit";
        }
        if(fruit.getQuantity() == null || fruit.getQuantity() == 0) {
            fruit.setQuantity(1);
        }
        //Check if the user has entered a duplicate.  If so, combine them.
        String checkName = fruit.getType().strip().toLowerCase();

        Boolean found = false;
        //Loop thru the existing cheeses to check for a duplicate.
        for (Fruit fruitItem : fruitService.getFruit()){
            if(fruitItem.getType().strip().toLowerCase().equals(checkName)) {
                found = true;
                fruitService.incrementFruit(fruitItem, fruit.getQuantity());
                fruitService.save(fruitItem);
            }
        }
        if(!found) {
            fruitService.save(fruit);
        }

        model.addAttribute("newfruit", fruit);
        model.addAttribute("fruit", fruit);
        System.out.println("SUCCESSFULLY ADDED.");
        return "addedfruit";
    }

    //Shelf.

    @Autowired
    private ShelfServiceImplementation shelfService;

    @ModelAttribute("shelves")
    public List<Shelf> getShelf() {
        return shelfService.getShelf();
    }


    @PostMapping("/shelf/{id}")
    public String deleteShelf(@PathVariable("id") Long id, Model model) {
        //Loop through the cheeses find the one that has that id decrement its
        //quantity by one.
        for(Shelf shelfItem : shelfService.getShelf()) {
            if(shelfItem.getId().equals(id)) {
                if(shelfItem.getQuantity() > 2 || shelfItem.getQuantity() == 2) {
                    shelfService.decrementShelf(shelfItem);
                    shelfService.save(shelfItem);
                }
                else {
                    shelfService.deleteShelfById(shelfItem.getId());
                }
            }
        }
        model.addAttribute("shelves", shelfService.getShelf());
        return "index";
    }

    @GetMapping("/addshelf")
    public String addShelfPage(Model model) {

        Shelf newshelf = new Shelf();
        model.addAttribute("shelf", newshelf);

        if(!model.containsAttribute("shelves")) {
            model.addAttribute("shelves", shelfService.getShelf());
        }

        return "addshelf";
    }

    //Do not make a new one. You must save the one from the model attribute.
    @PostMapping("/addshelf")
    public String saveShelf(@Valid Shelf shelf, BindingResult result, Model model) {
        model.addAttribute("errormsg", "You have encountered an error!");
        if (result.hasErrors()) {
            System.out.println("THERE WAS AN ERROR");
            System.out.println(result.getAllErrors());
            return "addshelf";
        }
        if(shelf.getQuantity() == null || shelf.getQuantity() == 0) {
            shelf.setQuantity(1);
        }
        //Check if the user has entered a duplicate.  If so, combine them.
        String checkName = shelf.getType().strip().toLowerCase();

        Boolean found = false;
        //Loop thru the existing cheeses to check for a duplicate.
        for (Shelf shelfItem : shelfService.getShelf()){
            if(shelfItem.getType().strip().toLowerCase().equals(checkName)) {
                found = true;
                shelfService.incrementShelf(shelfItem, shelf.getQuantity());
            }
        }
        if(!found) {
            shelfService.save(shelf);
        }

        model.addAttribute("newshelf", shelf);
        model.addAttribute("shelf", shelf);
        System.out.println("SUCCESSFULLY ADDED.");
        return "addedshelf";
    }

    //Shelf2

    @Autowired
    private Shelf2ServiceImplementation shelf2Service;

    @ModelAttribute("shelves2")
    public List<Shelf2> getShelf2() {
        return shelf2Service.getShelf2();
    }


    @PostMapping("/shelf2/{id}")
    public String deleteShelf2(@PathVariable("id") Long id, Model model) {
        //Loop through the cheeses find the one that has that id decrement its
        //quantity by one.
        for(Shelf2 shelf2Item : shelf2Service.getShelf2()) {
            if(shelf2Item.getId().equals(id)) {
                if(shelf2Item.getQuantity() > 2 || shelf2Item.getQuantity() == 2) {
                    shelf2Service.decrementShelf2(shelf2Item);
                    shelf2Service.save(shelf2Item);
                }
                else {
                    shelf2Service.deleteShelf2ById(shelf2Item.getId());
                }
            }
        }
        model.addAttribute("shelves2", shelf2Service.getShelf2());
        return "index";
    }

    @GetMapping("/addshelf2")
    public String addShelf2Page(Model model) {

        Shelf2 newshelf2 = new Shelf2();
        model.addAttribute("shelf2", newshelf2);

        if(!model.containsAttribute("shelves2")) {
            model.addAttribute("shelves2", shelf2Service.getShelf2());
        }

        return "addshelf2";
    }

    //Do not make a new one. You must save the one from the model attribute.
    @PostMapping("/addshelf2")
    public String saveShelf2(@Valid Shelf2 shelf2, BindingResult result, Model model) {
        model.addAttribute("errormsg", "You have encountered an error!");
        if (result.hasErrors()) {
            System.out.println("THERE WAS AN ERROR");
            System.out.println(result.getAllErrors());
            return "addshelf2";
        }
        if(shelf2.getQuantity() == null || shelf2.getQuantity() == 0) {
            shelf2.setQuantity(1);
        }
        //Check if the user has entered a duplicate.  If so, combine them.
        String checkName = shelf2.getType().strip().toLowerCase();

        Boolean found = false;
        //Loop thru the existing cheeses to check for a duplicate.
        for (Shelf2 shelf2Item : shelf2Service.getShelf2()){
            if(shelf2Item.getType().strip().toLowerCase().equals(checkName)) {
                found = true;
                shelf2Service.incrementShelf2(shelf2Item, shelf2.getQuantity());
            }
        }
        if(!found) {
            shelf2Service.save(shelf2);
        }

        model.addAttribute("newshelf2", shelf2);
        model.addAttribute("shelf2", shelf2);
        System.out.println("SUCCESSFULLY ADDED.");
        return "addedshelf2";
    }

    //Shelf3

    @Autowired
    private Shelf3ServiceImplementation shelf3Service;

    @ModelAttribute("shelves3")
    public List<Shelf3> getShelf3() {
        return shelf3Service.getShelf3();
    }


    @PostMapping("/shelf3/{id}")
    public String deleteShelf3(@PathVariable("id") Long id, Model model) {
        //Loop through the cheeses find the one that has that id decrement its
        //quantity by one.
        for(Shelf3 shelf3Item : shelf3Service.getShelf3()) {
            if(shelf3Item.getId().equals(id)) {
                if(shelf3Item.getQuantity() > 2 || shelf3Item.getQuantity() == 2) {
                    shelf3Service.decrementShelf3(shelf3Item);
                    shelf3Service.save(shelf3Item);
                }
                else {
                    shelf3Service.deleteShelf3ById(shelf3Item.getId());
                }
            }
        }
        model.addAttribute("shelves3", shelf3Service.getShelf3());
        return "index";
    }

    @GetMapping("/addshelf3")
    public String addShelf3Page(Model model) {

        Shelf3 newshelf3 = new Shelf3();
        model.addAttribute("shelf3", newshelf3);

        if(!model.containsAttribute("shelves3")) {
            model.addAttribute("shelves3", shelf3Service.getShelf3());
        }

        return "addshelf3";
    }

    //Do not make a new one. You must save the one from the model attribute.
    @PostMapping("/addshelf3")
    public String saveShelf3(@Valid Shelf3 shelf3, BindingResult result, Model model) {
        model.addAttribute("errormsg", "You have encountered an error!");
        if (result.hasErrors()) {
            System.out.println("THERE WAS AN ERROR");
            System.out.println(result.getAllErrors());
            return "addshelf3";
        }
        if(shelf3.getQuantity() == null || shelf3.getQuantity() == 0) {
            shelf3.setQuantity(1);
        }
        //Check if the user has entered a duplicate.  If so, combine them.
        String checkName = shelf3.getType().strip().toLowerCase();

        Boolean found = false;
        //Loop thru the existing cheeses to check for a duplicate.
        for (Shelf3 shelf3Item : shelf3Service.getShelf3()){
            if(shelf3Item.getType().strip().toLowerCase().equals(checkName)) {
                found = true;
                shelf3Service.incrementShelf3(shelf3Item, shelf3.getQuantity());
            }
        }
        if(!found) {
            shelf3Service.save(shelf3);
        }

        model.addAttribute("newshelf3", shelf3);
        model.addAttribute("shelf3", shelf3);
        System.out.println("SUCCESSFULLY ADDED.");
        return "addedshelf3";
    }

    //Door

    @Autowired
    private DoorServiceImplementation doorServiceImplementation;

    @ModelAttribute("doors")
    public List<Door> getDoor() {
        return doorServiceImplementation.getDoor();
    }


    @PostMapping("/door/{id}")
    public String deleteDoor(@PathVariable("id") Long id, Model model) {
        //Loop through the cheeses find the one that has that id decrement its
        //quantity by one.
        for(Door doorItem : doorServiceImplementation.getDoor()) {
            if(doorItem.getId().equals(id)) {
                if(doorItem.getQuantity() > 2 || doorItem.getQuantity() == 2) {
                    doorServiceImplementation.decrementDoor(doorItem);
                    doorServiceImplementation.save(doorItem);
                }
                else {
                    doorServiceImplementation.deleteDoorById(doorItem.getId());
                }
            }
        }
        model.addAttribute("doors", doorServiceImplementation.getDoor());
        return "index";
    }

    @GetMapping("/adddoor")
    public String addDoorPage(Model model) {

        Door door = new Door();
        model.addAttribute("door", door);
        model.addAttribute("newdoor", door);

        if(!model.containsAttribute("doors")) {
            model.addAttribute("doors", doorServiceImplementation.getDoor());
        }

        return "adddoor";
    }

    //Do not make a new one. You must save the one from the model attribute.
    @PostMapping("/adddoor")
    public String saveDoor(@Valid Door door, BindingResult result, Model model) {
        model.addAttribute("errormsg", "You have encountered an error!");
        if (result.hasErrors()) {
            System.out.println("THERE WAS AN ERROR");
            System.out.println(result.getAllErrors());
            return "adddoor";
        }
        if(door.getQuantity() == null || door.getQuantity() == 0) {
            door.setQuantity(1);
        }
        //Check if the user has entered a duplicate.  If so, combine them.
        String checkName = door.getType().strip().toLowerCase();

        Boolean found = false;
        //Loop thru the existing cheeses to check for a duplicate.
        for (Door doorItem : doorServiceImplementation.getDoor()){
            if(doorItem.getType().strip().toLowerCase().equals(checkName)) {
                found = true;
                doorServiceImplementation.incrementDoor(doorItem, door.getQuantity());
            }
        }
        if(!found) {
            doorServiceImplementation.save(door);
        }

        model.addAttribute("newdoor", door);
        model.addAttribute("dooor", door);
        System.out.println("SUCCESSFULLY ADDED.");
        return "addeddoor";
    }

}
