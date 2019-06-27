package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    //TODO 1.1: (done?)
    // 1a: Default (no-argument) constructor: This is required, and doesn't need a any code within its body.
    // It will only be used by Hibernate in the process of creating objects from data retrieved from the database.
    // 1b: A constructor that accepts a parameter to set name

   // public Category () {} // default constructor - updated per in class demo

    public Category(String name) {
        this.name = name;
    }

    public Category() { } // default cons\structor for hibernate
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
}
