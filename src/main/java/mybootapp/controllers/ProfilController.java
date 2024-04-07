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

@Controller
public class ProfilController {

    @Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping("/profil")
    public String mesSorties(Model model) {
        // Récupérer l'utilisateur connecté
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Récupérer les sorties de l'utilisateur connecté
        List<Sortie> userSorties = sortieRepository.findSortiesByCreator(Long.valueOf(auth.getName()));

        // Ajouter les sorties à l'objet Model
        model.addAttribute("userSorties", userSorties);

        return "profil";
    }
}
