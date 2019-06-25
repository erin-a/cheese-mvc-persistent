package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping
public class CategoryController {

    // TODO 1.3 Adding Categories  done?
    @Autowired // there's a warning about using this this in instructions part 1
    private CategoryDao categoryDao; //This creates a private field categoryDao of type CategoryDao. This object
    // will be the mechanism with which we interact with objects stored in the database. Recall that Spring will do
    // the work of creating a class that implements CategoryDao and putting one of those objects in the categoryDao
    // field when the application starts up. And all of this is thanks to the @Autowired annotation.

    //This code would need to be added to each controller class that you want to have access to the persistent
    // collections defined within categoryDao.


    // TO DO 1.4 / 1.5A done?
    //  1.4 The index handler should correspond to the route "" (that is, the path /category),
    //  and it should retrieve the list of all categories. This is done via the categoryDao object: categoryDao.findAll()
    //  returns a collection (actually, an Iterable) of all Category objects managed by categoryDao. Use this snippet to
    //  retrieve the list of categories, and then pass the list into the view by adding it to model. Also add a "title"
    //  to the model ("Categories" works).
    //  1.5A The handler should render the index.html template that you just created. This view should display an unordered
    //  list (that is, a <ul>) of category names. The list will look a bit plain for now, but we will make it more
    //  interesting later on.
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("Categories", categoryDao.findAll());
    }

    // TODO 1.6
    //  Create an add handler within CategoryController with input parameter Model model.
    //  It should create a new Category object using the default constructor and pass it into the view with key
    //  "category" (you can do this with the shorthand model.addAttribute(new Category()) ; note the omission of a
    //  string/key argument).
    //  ***Add the title "Add Category" to model as well.***
    //  The add handler should accept GET requests at /category/add (recall that you set the path segment "category"
    //  at the controller level already). The handler should render the category/add template (we'll add this template
    //  in a moment).
    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category"); //trying to: Add the title "Add Category" to model as well.
        return "category/add";
    }


    // TODO 1.7 Create another add handler that accepts POST requests at /category/add. Within this second add handler:
    //  - Determine whether or not there are any validation errors.
    //      - If there are, return the form at category/add
    //      - If the form submission is valid:
    //          - Save the new Category object by calling categoryDao.save(category).
    //          - Redirect to the index handler for CategoryController by returning the string "redirect:".
    @RequestMapping(value="add",method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("titl", "Add Category");
            return "category/add";}
        categoryDao.save(category);
            return "redirect:";
        }


    }
}
