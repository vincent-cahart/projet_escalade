package mybootapp.controllers;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Category;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur pour la page d'accueil de l'application.
 * Affiche les catégories et les sorties associées.
 */
@Controller
public class IndexController {

    @Autowired
    private JpaDao jpaDao;

    /**
     * Méthode de gestion de la requête GET pour la racine ("/").
     * Elle récupère les catégories et les sorties pour les afficher sur la page d'accueil.
     * 
     * @param model Modèle de Spring MVC pour ajouter des attributs qui seront utilisés dans la vue.
     * @return Le nom de la vue à rendre, ici "index".
     */
    @GetMapping("/")
    public String index(Model model) {
        // Récupération et organisation des sorties par catégorie
        List<Category> categories = jpaDao.findAllCategories();
        List<Sortie> sorties = jpaDao.findAllSorties();
        Map<Category, List<Sortie>> sortiesByCategory = organiseSortiesByCategory(categories, sorties);

        // Ajout des données au modèle
        model.addAttribute("categories", categories);
        model.addAttribute("sortiesByCategory", sortiesByCategory);

        return "index"; // Nom de la vue (par exemple index.html ou index.jsp)
    }

    /**
     * Organise les sorties par catégorie dans une map pour une utilisation facile dans la vue.
     * 
     * @param categories La liste des catégories disponibles.
     * @param sorties La liste de toutes les sorties.
     * @return Une map associant chaque catégorie à sa liste de sorties.
     */
    private Map<Category, List<Sortie>> organiseSortiesByCategory(List<Category> categories, List<Sortie> sorties) {
        Map<Category, List<Sortie>> sortiesByCategory = new LinkedHashMap<>();
        for (Category category : categories) {
            // Initialisation de la liste des sorties par catégorie
            sortiesByCategory.put(category, new LinkedHashMap<>());
        }
        for (Sortie sortie : sorties) {
            // Ajout de la sortie dans la catégorie correspondante
            sortiesByCategory.get(sortie.getCategory()).add(sortie);
        }
        return sortiesByCategory;
    }
}
