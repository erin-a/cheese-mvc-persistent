package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;


    @ManyToMany //this class menu is related to the cheese class by a many to many relationship, and that this
    // collection (list of cheese objects) should be one side of how that relationship is mapped - so for a given
    // menu object, this particular cheeses on it
    private List<Cheese> cheeses;

    public Menu() {
    } // default constructor

    public Menu(String name){
        this.name = name;
    } // I think we need this to pull the name, we had to add it in the addmenutitemform step pass in the menu and cheeses so I think we need to pass in the name here also

    public void addItem(Cheese item) {
        cheeses.add(item);
    } // adds the given item to the list

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

}



