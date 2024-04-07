package mybootapp.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

// Indique que cette classe est un composant de service géré par Spring. 
// Spring va créer une instance singleton de cette classe et l'insérer là où elle est nécessaire.
@Service
public class SecureService {

    // Garantit que cette méthode ne peut être accédée que par les utilisateurs ayant l'autorité 'ADMIN'.
    @PreAuthorize("hasAuthority('ADMIN')")
    public String helloAdmin() {
        return "Hello";
    }

    // Cette méthode ne peut être accédée que si le userName fourni correspond au nom d'utilisateur 
    // de l'utilisateur actuellement authentifié. C'est utile pour les opérations spécifiques à l'utilisateur,
    // où un utilisateur ne devrait pouvoir agir que sur ses propres données.
    @PreAuthorize("#userName == principal.username")
    public String helloForUser(String userName) {
        return "Hello " + userName;
    }

    // Démontre l'utilisation d'une expression de sécurité personnalisée. 
    // '@securityChecker' fait référence à un bean défini dans le contexte d'application Spring.
    // La méthode 'isOk' de ce bean sera appelée avec le nom d'utilisateur de l'utilisateur actuellement authentifié 
    // comme argument. L'accès est accordé si la méthode retourne vrai. 
    // Cette approche est puissante pour mettre en œuvre des exigences de sécurité complexes.
    @PreAuthorize("@securityChecker.isOk(principal.username)")
    public String helloSecuredByCode() {
        return "helloSecuredByCode is OK ";
    }

    // Vous pouvez ajouter d'autres méthodes sécurisées selon les besoins, 
    // en utilisant les annotations de Spring Security pour définir les exigences de sécurité précises 
    // pour chaque opération. Cela pourrait impliquer de vérifier les rôles des utilisateurs, 
    // d'évaluer des expressions impliquant des paramètres de méthode, 
    // ou d'invoquer une logique de sécurité personnalisée à travers des beans gérés par Spring.

    // Note : Pour que les expressions @PreAuthorize fonctionnent, assurez-vous que la sécurité des méthodes 
    // est activée dans votre configuration de sécurité Spring. Cela implique généralement d'ajouter 
    // l'annotation @EnableGlobalMethodSecurity à une classe de configuration avec les attributs appropriés 
    // définis (par exemple, prePostEnabled = true).
}
