package mybootapp.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

// Marks this class as a Spring-managed service component.
@Service
public class SecureService {

    // Ensures that this method can only be accessed by users with the 'ADMIN' authority.
    @PreAuthorize("hasAuthority('ADMIN')")
    public String helloAdmin() {
        return "Hello";
    }

    // This method can only be accessed if the provided userName matches the username of the currently authenticated user.
    // This is useful for user-specific operations where a user should only be able to act on their own data.
    @PreAuthorize("#userName == principal.username")
    public String helloForUser(String userName) {
        return "Hello " + userName;
    }

    // Demonstrates the use of a custom security expression. The '@securityChecker' references a bean defined in the
    // Spring application context. The 'isOk' method of that bean will be called with the username of the currently
    // authenticated user as the argument. Access is granted if the method returns true.
    // This approach is powerful for implementing complex security requirements.
    @PreAuthorize("@securityChecker.isOk(principal.username)")
    public String helloSecuredByCode() {
        return "helloSecuredByCode is OK ";
    }

    // Add additional secured methods as necessary, using Spring Security annotations to define the precise
    // security requirements for each operation. This could involve checking user roles, evaluating expressions
    // involving method parameters, or invoking custom security logic through Spring-managed beans.

    // Note: For the @PreAuthorize expressions to work, ensure that method security is enabled in your
    // Spring Security configuration. This typically involves adding the @EnableGlobalMethodSecurity annotation
    // to a configuration class with the appropriate attributes set (e.g., prePostEnabled = true).
}
