package mybootapp;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Contrôleur pour afficher les informations du {@link Principal} actuellement authentifié.
 * <p>
 * Ce contrôleur répond aux requêtes sur le chemin "/principal" et renvoie une représentation textuelle
 * de l'objet {@link Principal}, typiquement les détails de l'utilisateur authentifié.
 * </p>
 * 
 * @author Votre Nom
 */
@Controller
@RequestMapping("/principal")
public class ShowPrincipal {

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Gère la requête GET sur "/principal" et renvoie les informations du {@link Principal}.
     * 
     * @param p Le {@link Principal} injecté par Spring Security représentant l'utilisateur authentifié.
     * @return La représentation textuelle de l'objet {@link Principal}.
     */
    @ResponseBody
    @RequestMapping("")
    public String show(Principal p) {
        logger.info("Affichage de l'utilisateur " + p);
        return p.toString();
    }

}
