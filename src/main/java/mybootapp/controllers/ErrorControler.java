package mybootapp.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Contrôleur conseil pour la gestion centralisée des exceptions dans l'application.
 */
@ControllerAdvice
public class ErrorControler implements ErrorController {

    /**
     * Gère les exceptions de type NullPointerException.
     * Cette méthode intercepte toutes les NullPointerException levées dans l'application
     * et retourne un message d'erreur standardisé.
     * 
     * @param e L'exception interceptée.
     * @return Un message indiquant une erreur de pointeur nul.
     */
    @ResponseBody
    @ExceptionHandler({NullPointerException.class})
    public String handleNullPointerException(Exception e) {
        // Journalisation de l'exception
        System.err.println("-- NullPointerException:");
        e.printStackTrace(System.err);
        
        // Retour d'un message d'erreur
        return "Null Pointer Error";
    }

    /**
     * Gère toutes les autres exceptions non spécifiquement gérées par d'autres méthodes @ExceptionHandler.
     * Cette méthode fournit un point de capture générique pour toutes les exceptions non capturées ailleurs.
     * 
     * @param e L'exception interceptée.
     * @return Un message indiquant une erreur générique.
     */
    @ResponseBody
    @ExceptionHandler
    public String handleOtherException(Exception e) {
        // Journalisation de l'exception
        System.err.println("-- Other Exception:");
        e.printStackTrace(System.err);
        
        // Retour d'un message d'erreur générique
        return "Other Error";
    }

}
