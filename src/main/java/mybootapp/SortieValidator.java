package mybootapp;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import mybootapp.jpa.model.Sortie;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Service de validation pour les instances de {@link Sortie}.
 * <p>
 * Ce validateur vérifie la conformité des champs d'une sortie avec les règles de validation
 * spécifiques (par exemple, non vide, format correct, etc.).
 * </p>
 * 
 * @author Votre Nom
 */
@Service
public class SortieValidator implements Validator {

    /**
     * Détermine si le validateur peut être appliqué à une classe donnée.
     * 
     * @param clazz La classe à valider.
     * @return true si la classe est assignable à {@link Sortie}, false sinon.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Sortie.class.isAssignableFrom(clazz);
    }

    /**
     * Effectue la validation sur l'objet cible et enregistre les erreurs rencontrées dans l'objet {@link Errors}.
     * 
     * @param target L'objet à valider.
     * @param errors L'objet pour enregistrer les erreurs de validation.
     */
    @Override
    public void validate(Object target, Errors errors) {
        Sortie sortie = (Sortie) target;

        // Validation du nom
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "sortie.name.required", "Le nom est obligatoire.");

        // Validation de la description
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "sortie.description.required", "La description est obligatoire.");

        // Validation du site web avec expression régulière
        validateSiteWeb(sortie, errors);

        // Validation de la date de sortie
        validateDateSortie(sortie, errors);

        // Validation de la catégorie
        if (sortie.getCategory() == null) {
            errors.rejectValue("category", "sortie.category.required", "La catégorie est obligatoire.");
        }
    }

    private void validateSiteWeb(Sortie sortie, Errors errors) {
        String siteWeb = sortie.getSiteWeb();
        if (siteWeb != null && !siteWeb.isEmpty()) {
            String siteWebRegex = "^(http://|https://)?(www\\.)?[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})+$";
            if (!Pattern.matches(siteWebRegex, siteWeb)) {
                errors.rejectValue("siteWeb", "sortie.siteWeb.invalidFormat", "Le format du site web est invalide.");
            }
        }
    }

    private void validateDateSortie(Sortie sortie, Errors errors) {
        if (sortie.getDateSortie() == null) {
            errors.rejectValue("dateSortie", "sortie.dateSortie.required", "La date de sortie est obligatoire.");
        } else {
            Date currentDate = new Date();
            if (sortie.getDateSortie().before(currentDate)) {
                errors.rejectValue("dateSortie", "sortie.dateSortie.invalid", "La date de sortie doit être dans le futur.");
            }
        }
    }
}
