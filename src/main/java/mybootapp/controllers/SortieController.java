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

    @GetMapping
    public String sortieDetails(@RequestParam(name = "sortieId") Long sortieId, Model model) {
        Sortie sortie = jpaDao.findSortieDetails(sortieId);
        model.addAttribute("sortie", sortie);
        model.addAttribute("editable", canUserEdit(sortieId));
        return "sortie";
    }

    @GetMapping("/modifier")
    public String afficherFormulaireModificationSortie(@RequestParam(name = "sortieId") Long sortieId, Model model) {
        Sortie sortie = jpaDao.findSortieDetails(sortieId);
        model.addAttribute("sortie", sortie);
        model.addAttribute("categories", jpaDao.findAllCategories());
        model.addAttribute("editable", canUserEdit(sortieId));
        return "sortieForm";
    }

    @PostMapping("/modifier")
    public String modifierSortie(@ModelAttribute("sortie") Sortie sortieModifiee, BindingResult bindingResult, Model model) {
        sortieValidator.validate(sortieModifiee, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", jpaDao.findAllCategories());
            model.addAttribute("editable", canUserEdit(sortieModifiee.getId()));
            return "sortieForm";
        }


        if (!canUserEdit(sortieModifiee.getId())) {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à modifier cette sortie.");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(auth.getName());
        sortieModifiee.setCreator(jpaDao.findMemberById(userId));
        jpaDao.saveSortie(sortieModifiee);
        return "redirect:/profil";
    }
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    private boolean canUserEdit(Long idOfSortie) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(auth.getName());
        return jpaDao.findSortieDetails(idOfSortie).getCreator().getId() == userId;
    }
}
