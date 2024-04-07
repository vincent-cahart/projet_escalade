package mybootapp;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import mybootapp.jpa.model.Sortie;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class SortieValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Sortie.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sortie sortie = (Sortie) target;

        // Validation du nom
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "sortie.name.required");

        // Validation de la description
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "sortie.description.required");

        // Validation du site web
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "siteWeb", "sortie.siteWeb.required");
        String siteWeb = sortie.getSiteWeb();
        if (siteWeb != null && !siteWeb.isEmpty()) {
            String siteWebRegex = "^(http://|https://)?(www\\.)?[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})+$";
            if (!Pattern.matches(siteWebRegex, siteWeb)) {
                errors.rejectValue("siteWeb", "sortie.siteWeb.invalidFormat");
            }
        }

// Validation de la date de sortie (avec heure et minutes)
        if (sortie.getDateSortie() == null) {
            System.out.println("errroroooor");
            errors.rejectValue("dateSortie", "sortie.dateSortie.required");
        } else {
            Date currentDate = new Date();
            if (sortie.getDateSortie().before(currentDate)) {
                errors.rejectValue("dateSortie", "sortie.dateSortie.invalid");
            } else if (sortie.getDateSortie().equals(currentDate)) {
            }
        }

        // Validation de la catégorie
        if (sortie.getCategory() == null) {
            errors.rejectValue("category", "sortie.category.required");
        }

    }
}
