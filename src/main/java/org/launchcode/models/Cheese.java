package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @ManyToOne // specifying that there can be many cheeses for any one category.
    private Category category; //stores unique id of the category - primary key on category table becomes the foreign
    // key on the cheese table
    //Hibernate will create a column named category_id (based on the field name) and when a Cheese object is stored,
    // this column will contain the id of its category object. The data for the category object itself will go in the
    // table for the Category class.

    @ManyToMany(mappedBy = "cheeses") // hibernate will look for the "cheeses" on the other side of the many to many for hibernate to do the linking
    private List<Menu> menus; // you can't put many to many on both sides and expect it to work, hibernate needs more
    // information to determine what goes where, so the mapped by can go on either side because it's symmetric -
    // there's no parent/child relationship or one owning hte others

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //TODO done? 2.1c update to remove cheesetype
    public Category getCategory() {
        return category;
    }

    //TODO done? 2.1b update to remove cheesetype
    public void setCategory(Category category) {
        this.category = category;
    }
}
