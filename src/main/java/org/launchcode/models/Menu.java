package org.launchcode.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    }

    //public void addItem(Cheese Item) {
    //    cheeses.add(item);
    //}

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

    // may not be in video
    // public void setCheeses(List<Cheese> cheeses) {
    //    this.cheeses = cheeses;
    //}

}

