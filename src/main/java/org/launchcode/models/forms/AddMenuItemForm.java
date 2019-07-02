package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

    //menu and cheese id are pieces of data passed into our form handler and they specify the specific cheese object
    // and the specific menu object that should be related to each other (based on that form submission)
    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    private Iterable<Cheese> cheeses; // collection of cheeses will be used to render the dropdown to give the user the
    // set of options they can choose from to add to their menu

    private Menu menu; //a menu object itself, used when rendering the form

    public AddMenuItemForm() {} //a default constructor (needed to for model binding to work)

    //this constructor takes in a collection of cheeses and a menu and creates a new form
    // constructor that accepts and sets values for menu and cheeses
    public AddMenuItemForm(Iterable<Cheese> cheeses, Menu menu) {
        this.cheeses = cheeses;
        this.menu = menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public Menu getMenu() {
        return menu;
    }

    //accessors needed for this form are below
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

}
