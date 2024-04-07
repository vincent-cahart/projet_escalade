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
 * Contrôleur pour gérer la fonctionnalité de recherche au sein de l'application.
 */
@Controller
public class SearchController {

    @Autowired
    private JpaDao jpaDao; // Injection du DAO pour accéder à la base de données.

    /**
     * Gère les requêtes de recherche et affiche les résultats.
     * 
     * @param keyword Le mot-clé de recherche fourni par l'utilisateur.
     * @param model   L'objet Model pour passer des données à la vue.
     * @return Le nom de la vue à rendre.
     */
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        // Réalise l'opération de recherche en utilisant le mot-clé fourni.
        List<Sortie> searchResults = jpaDao.searchSortiesParCriteres(keyword);

        // Ajoute les résultats de la recherche au modèle, les rendant accessibles dans la vue.
        model.addAttribute("searchResults", searchResults);

        // Retourne le nom de la vue pour afficher les résultats de recherche.
        return "search"; // Cela devrait correspondre au nom de votre template de page de résultats de recherche.
    }
}
