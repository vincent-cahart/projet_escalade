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
 * Contrôleur pour gérer les requêtes liées aux catégories.
 */
@Controller
public class CategoryController {

    @Autowired
    private JpaDao jpaDao;

    /**
     * Affiche les détails d'une catégorie spécifique, y compris les sorties associées.
     * 
     * @param categoryId L'identifiant de la catégorie dont les détails sont demandés.
     * @param model Le modèle pour passer les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/categorie")
    public String categoryDetails(@RequestParam(name = "categorieId") Long categoryId, Model model) {
        // Recherche des sorties appartenant à la catégorie spécifiée
        List<Sortie> sorties = jpaDao.findSortiesByCategory(categoryId);
        
        // Ajout des données de la catégorie et des sorties associées au modèle
        model.addAttribute("category", jpaDao.findCategorieDetails(categoryId));
        model.addAttribute("sorties", sorties);
        
        // Retourne le nom de la vue à afficher
        return "categorie";
    }
}
