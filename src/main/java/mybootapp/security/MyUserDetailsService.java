package mybootapp.security;

import mybootapp.jpa.dao.repositories.MemberRepository;
import mybootapp.jpa.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Indique que cette classe est un service géré par Spring. 
// Spring va créer une instance de cette classe et l'injecter là où elle est nécessaire.
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    // Supposons que vous avez un bean PasswordEncoder configuré dans votre configuration de sécurité.
    // Il est utilisé pour encoder les mots de passe.
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Recherche d'un membre par son adresse e-mail dans la base de données.
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            // Si aucun membre n'est trouvé avec cet e-mail, une exception est lancée.
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'adresse e-mail: " + email);
        }

        // Convertit l'entité Member en UserDetails de Spring Security.
        // Ajustez les rôles et autorités selon les besoins de votre application.
        return org.springframework.security.core.userdetails.User
                // Utilisez l'ID comme champ de nom d'utilisateur pour les contextes de Spring Security.
                .withUsername(String.valueOf(member.getId()))
                // Assurez-vous que le mot de passe est encodé en utilisant le même PasswordEncoder que lors de l'enregistrement des mots de passe.
                .password(passwordEncoder.encode(member.getPassword()))
                .roles("USER") // Définissez les rôles selon vos besoins. Le préfixe ROLE_ est automatiquement ajouté.
                // Pour un contrôle plus fin, utilisez authorities() au lieu de roles() pour des permissions spécifiques.
                .build();
    }

}
