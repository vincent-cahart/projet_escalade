package mybootapp.controllers;

import mybootapp.jpa.dao.repositories.SortieRepository;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Contrôleur pour gérer les requêtes vers la page de profil de l'utilisateur.
 */
@Controller
public class ProfilController {

    @Autowired
    private SortieRepository sortieRepository;

    /**
     * Affiche la page de profil de l'utilisateur, y compris les sorties qu'il a créées.
     * 
     * @param model Le modèle pour passer les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/profil")
    public String afficherProfilUtilisateur(Model model) {
        // Récupère l'authentification actuelle pour obtenir le nom d'utilisateur (ou l'ID, selon votre configuration).
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // Dans cet exemple, username est utilisé pour l'authentification.

        // Supposons que vous avez une méthode dans SortieRepository pour trouver des sorties par nom d'utilisateur.
        // Cette étape est optimisée pour éliminer le besoin de convertir le nom d'utilisateur en ID d'abord,
        // à condition que votre modèle de données le permette.
        List<Sortie> userSorties = sortieRepository.findSortiesByUsername(username);

        if (userSorties.isEmpty()) {
            // Optionnel: Gérer le cas où l'utilisateur n'a créé aucune sortie.
            model.addAttribute("noSorties", true);
        }

        // Ajoute les sorties à l'objet Model pour les rendre accessibles dans la vue.
        model.addAttribute("userSorties", userSorties);

        // Retourne le nom de la vue (par exemple, profil.html dans templates sous src/main/resources).
        return "profil"; // Assurez-vous d'avoir un template appelé profil.html (ou .jsp, .ftl, selon votre techno).
    }

    // Vous pourriez avoir besoin d'implémenter findSortiesByUsername dans votre SortieRepository.
    // L'implémentation dépend de votre structure de données et de l'ORM que vous utilisez (par exemple, JPA, Hibernate).
}
