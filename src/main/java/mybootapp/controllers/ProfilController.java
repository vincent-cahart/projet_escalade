package mybootapp.controllers;

import mybootapp.jpa.dao.repositeries.SortieRepository;
import mybootapp.jpa.model.Sortie;
import mybootapp.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

/**
 * Contrôleur gérant les requêtes vers la page de profil de l'utilisateur.
 */
@Controller
public class ProfilController {

    @Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    /**
     * Affiche la page de profil de l'utilisateur avec ses sorties.
     * 
     * @param model Le modèle pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/profil")
    public String mesSorties(Model model) {
        // Récupérer l'identifiant de l'utilisateur connecté
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userDetailsService.findUserIdByUsername(auth.getName());

        if (userId == null) {
            // Gérer l'erreur si l'ID de l'utilisateur n'est pas trouvé (redirection ou message d'erreur)
            return "redirect:/error";
        }

        // Récupérer les sorties créées par l'utilisateur connecté
        List<Sortie> userSorties = sortieRepository.findSortiesByCreator(userId);

        // Ajouter les sorties à l'objet Model pour les rendre accessibles dans la vue
        model.addAttribute("userSorties", userSorties);

        return "profil"; // Nom du fichier de la vue (profil.html ou profil.jsp par exemple)
    }
}
