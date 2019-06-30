package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired  //
    private CheeseDao cheeseDao; // video doesn't have private on there

    @Autowired  // this means that springboot will create the class and the object and inject into the
    // controllers - this will result in this field having a non null value as a result
    private CategoryDao categoryDao; // when any controller code runs, this field will have a reference to an object that
    // implements the categoryDao interface

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    // displays all the cheeses in the system
    // we want to display a list of category options when the user is creating a new cheese, so we need to send a list
    // of all of the available categories into the form that will display this information.
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("category", categoryDao.findAll()); //TODO done? 2.1a update to remove cheesetype
        //  this returns a list like object that contains all of the categories, this returns an iterable, so it can be looped over
        return "cheese/add";
    }

    // this processes the form request and creates a new cheese object
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        // this creates/finishes the creation of the cheese object by adding the category
        Category cat = categoryDao.findOne(categoryId); // this pulls the correct category based on the category ID
        // - this sets the Category object to correspond to this ID by pulling it from the data layer
        newCheese.setCategory(cat); // sets the category of the new cheese to be the selected category
        cheeseDao.save(newCheese); // ads the cheese to the database
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) { //in video: for (int id : ids) instead
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }

    //new method in video
    // this handler includes the route ( it's mapped into the controller method as a request param  using the category
    // ID via query parameter)

    //@RequestMapping(value = "category", method=RequestMethod.GET)
   // public String category(Model model, RequestParam int cheeseId) //guessing this should this be cheeseId instead of id

    //Category cat = categoryDao.findOne(cheeseId); // when this method is called this finds the specific id of the category that was passed in
    //List<Cheese> cheeses = cat.getCheeses(); // this accesses that category's list of cheeses via Hibernate to populate the list
    //model.addAttribute("cheese", cheeses); // adds the attributes to the model
    //model.addAttribute("title", "Cheeses in Category: " + cat.getName()); //renders list
    //return "cheese/index";
}
