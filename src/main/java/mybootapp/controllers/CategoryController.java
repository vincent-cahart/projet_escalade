package mybootapp.controllers;



import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private JpaDao jpaDao;

    @GetMapping("/categorie")
    public String categoryDetails(@RequestParam(name = "categorieId") Long categoryId, Model model) {
        List<Sortie> sorties = jpaDao.findSortiesByCategory(categoryId);
        model.addAttribute("category", jpaDao.findCategorieDetails(categoryId));
        model.addAttribute("sorties", sorties);
        return "categorie";
    }
}
