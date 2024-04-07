package mybootapp.controllers;

import mybootapp.SortieValidator;
import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Sortie;
import mybootapp.jpa.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/sortie")
public class SortieController {

    @Autowired
    private JpaDao jpaDao;

    @Autowired
    private SortieValidator sortieValidator;

    // Affiche les détails d'une sortie spécifique
    @GetMapping
    public String sortieDetails(@RequestParam(name = "sortieId") Long sortieId, Model model) {
        Sortie sortie = jpaDao.findSortieDetails(sortieId);
        model.addAttribute("sortie", sortie);
        model.addAttribute("editable", canUserEdit(sortieId));
        return "sortie"; // Renvoie vers la vue des détails de la sortie
    }

    // Affiche le formulaire de modification d'une sortie
    @GetMapping("/modifier")
    public String afficherFormulaireModificationSortie(@RequestParam(name = "sortieId") Long sortieId, Model model) {
        Sortie sortie = jpaDao.findSortieDetails(sortieId);
        model.addAttribute("sortie", sortie);
        model.addAttribute("categories", jpaDao.findAllCategories()); // Charge toutes les catégories pour le formulaire
        model.addAttribute("editable", canUserEdit(sortieId));
        return "sortieForm"; // Renvoie vers le formulaire de modification de la sortie
    }

    // Traite la soumission du formulaire de modification d'une sortie
    @PostMapping("/modifier")
    public String modifierSortie(@ModelAttribute("sortie") Sortie sortieModifiee, BindingResult bindingResult, Model model) {
        sortieValidator.validate(sortieModifiee, bindingResult); // Validation de la sortie modifiée
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", jpaDao.findAllCategories());
            model.addAttribute("editable", canUserEdit(sortieModifiee.getId()));
            return "sortieForm"; // Renvoie au formulaire en cas d'erreur
        }

        // Vérifie si l'utilisateur a le droit de modifier cette sortie
        if (!canUserEdit(sortieModifiee.getId())) {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à modifier cette sortie.");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(auth.getName());
        sortieModifiee.setCreator(jpaDao.findMemberById(userId)); // Associe l'utilisateur actuel en tant que créateur de la sortie
        jpaDao.saveSortie(sortieModifiee);
        return "redirect:/profil"; // Redirection vers le profil de l'utilisateur après modification
    }

    // Configure le format de date accepté dans les formulaires
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // Vérifie si l'utilisateur actuel a le droit de modifier une sortie
    private boolean canUserEdit(Long idOfSortie) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(auth.getName());
        return jpaDao.findSortieDetails(idOfSortie).getCreator().getId() == userId;
    }
}
