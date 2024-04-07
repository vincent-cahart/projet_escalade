package mybootapp.controllers;


import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private JpaDao jpaDao;

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Sortie> searchResults = jpaDao.searchSortiesParCriteres(keyword);
        model.addAttribute("searchResults", searchResults);
        return "search";
    }
}
