package mybootapp.controllers;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for handling search functionality within the application.
 */
@Controller
public class SearchController {

    @Autowired
    private JpaDao jpaDao; // Injecting the DAO to access the database.

    /**
     * Handles search requests and displays the results.
     * 
     * @param keyword The search keyword provided by the user.
     * @param model   The Model object to pass data to the view.
     * @return The name of the view to render.
     */
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        // Perform the search operation using the provided keyword.
        List<Sortie> searchResults = jpaDao.searchSortiesParCriteres(keyword);

        // Add the search results to the model, making them accessible in the view.
        model.addAttribute("searchResults", searchResults);

        // Return the name of the view to render the search results.
        return "search"; // This should correspond to the name of your search results page template.
    }
}
