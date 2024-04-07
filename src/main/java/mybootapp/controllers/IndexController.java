package mybootapp.controllers;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Category;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * Contrôleur pour la page d'accueil de l'application.
 * Affiche les catégories et les sorties associées de manière organisée.
 */
@Controller
public class IndexController {

    @Autowired
    private JpaDao jpaDao;

    /**
     * Gère la requête GET pour la racine ("/"), récupérant et organisant les catégories et sorties.
     *
     * @param model Modèle pour passer des attributs à la vue.
     * @return Le nom de la vue à rendre ("index").
     */
    @GetMapping("/")
    public String index(Model model) {
        // Récupération des catégories. Cela pourrait être optimisé si la récupération des sorties incluait déjà les catégories.
        List<Category> categories = jpaDao.findAllCategories();

        // Appel optimisé pour récupérer directement les sorties organisées par catégorie.
        Map<Category, List<Sortie>> sortiesByCategory = jpaDao.findSortiesOrganisedByCategory();

        // Ajout des attributs au modèle pour la vue.
        model.addAttribute("categories", categories); // Peut-être redondant si les clés de sortiesByCategory suffisent.
        model.addAttribute("sortiesByCategory", sortiesByCategory);

        return "index"; // Correspond à index.html dans les templates
    }
}
