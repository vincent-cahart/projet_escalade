package mybootapp.controllers;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Category;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private JpaDao jpaDao;

    @GetMapping("/")
    public String index(Model model) {
// Récupérer toutes les catégories
        List<Sortie> sorties = jpaDao.findAllSorties();

        // Récupérer les sorties pour chaque catégorie
        Map<Category, List<Sortie>> sortiesByCategory = new LinkedHashMap<>();
        for(Category categorie : jpaDao.findAllCategories()){
            sortiesByCategory.put(categorie, new ArrayList<>());
        }
        for (Sortie sortie : sorties) {
            sortiesByCategory.get(sortie.getCategory()).add(sortie);
        }

        // Ajouter les données au modèle pour être affichées dans la vue
        model.addAttribute("categories", jpaDao.findAllCategories());
        model.addAttribute("sorties", jpaDao.findAllSorties());

        // Retourner le nom de la vue à afficher (index.jsp dans ce cas)
        return "index";
    }
}
