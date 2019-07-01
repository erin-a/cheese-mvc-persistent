package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller //added per instructions in part 3 "The MenuController Class and Views" section
@RequestMapping(value ="menu") //added per instructions in part 3 "The MenuController Class and Views" section
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    CheeseDao cheeseDao;

    @RequestMapping(value ="")
    public String index(Model model) {
        model.addAttribute("title", "Menus"); //adds a title attribute that says "Menus"
        model.addAttribute("menus", "menuDao.findAll()"); //shows a list of all available
        // menus by passing (under the attribute name menus) the result of calling menuDao.findAll()
        return "menu/index";
    }

    // adds a new menu (empty to start)  //displayAddMenuFrom
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model){
            model.addAttribute("title", "Add Menu"); // adds the title
            model.addAttribute(new Menu()); // passes a new menu object into the view to help build the form for validation
            return "menu/add";
    }

    // handler that adds a new menu  //processAddMenuForm
    @RequestMapping(value = "add", method=RequestMethod.POST)
    // this takes a model attribute that binds the menu object to this handler
    // remember that the errors object has to directly follow the object the model that the errors should be associated with
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors) {//
            if (errors.hasErrors()) {
                model.addAttribute("title", "Add Menu");
                return "menu/add"; //check for errors, if there are errors go back to the same form and display those errors to the user
            }
            menuDao.save(menu); //if no errors we will save the new menu item (the new object that was passed in via model binding)
            return "redirect:view/" + menu.getId(); //redirects by building up the path plus the menu Id, NOTE that we
            // never created an Id for this, so when you do a save on a hibernate managed class that will also generate
            // the id for that item when it adds it to the database
        }

    //this handler display the contents of a single menu via get request // displaySingleMenu
    // TODO 3.? Within the handler, retrieve the Menu object with the given ID using menuDao.
    //  Pass the given menu into the view.
    //  In the video lesson demonstrating this part of the application, the name, ID, and list of cheeses are
    //  each passed in separately to the view. Passing in the full Menu object, as instructed here, is more efficient.
    //
    @RequestMapping(value="view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId){ //we use the path variable to get the particular id
        // of the menu we want to display and that is inserted into the curly brackets menuId in the request mapping
        // value to configure the path
        Menu menu = menuDao.findOne(menuId); // this fetches the value of the particular menu we are looking at
        model.addAttribute(menu);
        model.addAttribute("title", "My Cheeses: ");
        //model.addAttribute("title", menu.getName()); //adds attribute - page title which is set to menu name
        //model.addAttribute("cheeses", menu.getCheeses()); //adds attribute - all the cheeses that are part of that menu
        //model.addAttribute("menu", menu.getId()); // passes in the specific menu's id -
        return "menu/view";
    }



    // displayAddMenuItem
    //diplays the form, the path variable annotation in the previous handler which identified which menu to add items to
    @RequestMapping(value="add-item", method = RequestMethod.GET) //was value="add-item/{menuId}"
    public String addItem(Model model, @PathVariable int menuId) {

        Menu menu = menuDao.findOne(menuId); //fetches the menu we want to add items to via the identifier (menuId) out of the database
        AddMenuItemForm form = new AddMenuItemForm( //need to create this AddMenuItemsForm object - we will create a
                // model class specifically to render or process a form - this is a handy way to do validation when
                // the form you're looking at doesn't correspond with a specific class otherwise. This isn't creating a
                // new object, but rather a new relationship between objects SO there's not a (natural) model class we
                // can use for model binding so we created this one that will be used to both render the form and process
                // it - this inside the forms package
                // so this AddMenuItemForm model class has all the data related to displaying and processing the form
                // we're looking at to create a new instance of it using the two lines below and passes it all into the view
                cheeseDao.findAll(), //pulls the cheeses to add to the new instance
                menu); //pulls the menu object in question to add to the new instance

        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", form);
        return "menu/add-item";
    }

    // processes the form //processAddMenuItem
    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model,
                          @ModelAttribute @Valid AddMenuItemForm form, //takes in an AddMenutItemForm (object), it's
                          // using model binding to build that object up for us based on the submitted form data
                          // @valid validates the form data to confirm that this should be a valid object
                          Errors errors) { //allows us to check if the validation caught any errors
        if (errors.hasErrors()) { //first thing in the method, checks for errors
            model.addAttribute("form", form);  //if errors, submit back to the same form with error messages
            return "menu/add-item";
        }

        // if there are no errors do these things
        Cheese theCheese = cheeseDao.findOne(form.getCheeseId()); //retrieves the id of the cheese that user submitted
        // (user submits the id of the item they want to add to the menu) the cheeseDao interface finds that particular
        // cheese with that particular id and then interface returns that id to us in the handler
        Menu theMenu = menuDao.findOne(form.getMenuId()); // do the same thing for the menu - like we did for cheese -
        // we retrieve the object associated with their respective Dao (data access objects)


        theMenu.addItem(theCheese); //we add the item into the menu
        menuDao.save(theMenu); //this pushes these changes to the database object, this is required to get something
        // into the database in a persistent way - it saves it - so it saves the updated status/information for an
        // existing item

        return "redirect:/menu/view" + theMenu.getId(); //returns the menu view for the menu in question and displays it
        // with the new item
    }
}







