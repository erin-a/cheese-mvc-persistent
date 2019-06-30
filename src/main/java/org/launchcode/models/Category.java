package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id") // tells it which column on the cheese table should determine the cheeses that
    // are part of this list - connects the category id to the cheese table //Each one category will have many cheeses,
    // but each cheese can have only one category
    // This tells Hibernate to use the category_id column of the cheese table to determine which cheese belong to a
    // given category.
    // This tells Hibernate to use the category_id column of the cheese table to determine which cheese belong to a
    // given category.
    private List<Cheese> cheeses = new ArrayList<>();

    //TODOne 1.1: (done?)
    // 1a: Default (no-argument) constructor: This is required, and doesn't need a any code within its body.
    // It will only be used by Hibernate in the process of creating objects from data retrieved from the database.
    // 1b: A constructor that accepts a parameter to set name

    public Category(String name) {
        this.name = name;
    }

    public Category() { } // default constructor for hibernate
    //default void Category(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() { return cheeses; }
}
