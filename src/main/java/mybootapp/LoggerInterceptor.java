package mybootapp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Intercepteur de requêtes HTTP pour logger les accès et restreindre l'accès à certains clients.
 * <p>
 * Cette classe implémente les méthodes de l'interface {@link HandlerInterceptor} pour intercepter
 * les requêtes avant et après leur traitement par le contrôleur, ainsi qu'après la génération de la vue.
 * </p>
 *
 * @author Votre Nom
 */
public class LoggerInterceptor implements HandlerInterceptor {

    private static final Log log = LogFactory.getLog(LoggerInterceptor.class);

    /**
     * Pré-traite la requête HTTP avant son traitement par le contrôleur.
     * <p>
     * Log l'adresse IP du client et restreint l'accès à l'application aux requêtes provenant de localhost.
     * </p>
     *
     * @param request La requête HTTP à traiter.
     * @param response La réponse HTTP à envoyer.
     * @param handler L'objet qui gère la requête.
     * @return true si la requête doit être traitée, false sinon.
     * @throws Exception en cas d'erreur lors du traitement de la requête.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var client = request.getRemoteAddr();
        log.info("Pré-traitement de la requête provenant de " + client);
        switch (client) {
            case "127.0.0.1":
            case "0:0:0:0:0:0:0:1":
                return true;
            default:
                response.getWriter().printf("Accès uniquement depuis 127.0.0.1");
                return false;
        }
    }

    /**
     * Post-traite la requête HTTP après son traitement par le contrôleur mais avant la génération de la vue.
     *
     * @param request La requête HTTP traitée.
     * @param response La réponse HTTP générée.
     * @param handler L'objet qui a géré la requête.
     * @param modelAndView Le modèle et la vue qui doivent être retournés.
     * @throws Exception en cas d'erreur lors du post-traitement de la requête.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Post-traitement de la requête");
    }

    /**
     * Traitement après l'achèvement complet de la requête, après la génération et l'envoi de la réponse.
     *
     * @param request La requête HTTP traitée.
     * @param response La réponse HTTP envoyée.
     * @param handler L'objet qui a géré la requête.
     * @param exception L'exception levée pendant le traitement de la requête, si présente.
     * @throws Exception en cas d'erreur lors du traitement final de la requête.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        log.info("Traitement après achèvement de la requête");
    }
}
