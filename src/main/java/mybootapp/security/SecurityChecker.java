package mybootapp.security;

import org.springframework.stereotype.Service;

// Marque cette classe comme un composant service géré par Spring. Le nom "securityChecker"
// spécifie un identifiant de bean personnalisé, le rendant plus facile à référencer dans les expressions SpEL.
@Service("securityChecker")
public class SecurityChecker {

    // Cette méthode fournit une logique de vérification de sécurité personnalisée. Ici, c'est un simple
    // exemple qui accorde l'accès si le nom d'utilisateur correspond à une valeur spécifique ("aaa").
    // Dans un scénario réel, cette méthode pourrait impliquer des vérifications complexes telles que
    // la vérification des permissions des utilisateurs stockées dans une base de données, la vérification par rapport aux informations de session actuelle, ou l'application de règles de sécurité spécifiques à l'entreprise.
    public boolean isOk(String userName) {
        // Retourne vrai si le nom d'utilisateur est "aaa", faux autrement.
        // Cette condition simple peut être remplacée ou étendue avec une logique
        // plus complexe adaptée aux besoins de votre application.
        return "aaa".equals(userName);
    }

    // La flexibilité de cette approche permet d'appliquer presque n'importe quelle condition de sécurité personnalisée.
    // La méthode pourrait interagir avec d'autres composants de votre application, effectuer des requêtes en base de données,
    // communiquer avec des systèmes externes, ou exécuter toute autre logique nécessaire pour prendre une
