package mybootapp.security;

import java.util.List;

import mybootapp.jpa.dao.repositeries.XUserRepository;
import mybootapp.jpa.model.XUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurity {

    @Autowired
    XUserRepository userRepo;

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers("/webjars/**");
        };
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] anonymousRequests = { "/", //
                "/webjars/**",
        };
        String[] adminRequests = { //

        };
        String[] connectedRequests = { //
                "/profil/**", "/profil**",
                "/sortie/modifier**", "/sortie/modifier**",
        };

        http.authorizeHttpRequests(config -> {//
            config.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll();
            // Pour tous
            config.requestMatchers(anonymousRequests).permitAll();//
            config.requestMatchers(connectedRequests).authenticated();//
            // Pour les admins
            config.requestMatchers(adminRequests).hasAnyAuthority("ADMIN");//
            // Pour les autres
            config.anyRequest().permitAll();
        });
        // Nous autorisons un formulaire de login
        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
        // Nous autorisons un formulaire de logout
        http.logout(config -> {
            config.permitAll();
            config.logoutSuccessUrl("/");
        });
        // Nous activons CSRF pour les actions protégées
        http.csrf(config -> {
            config.ignoringRequestMatchers(anonymousRequests);
        });
        return http.build();
    }

    /*
     * Définition des utilisateurs en BD.
     */
    @PostConstruct
    public void init() {
        var encoder = passwordEncoder();
        var aa = new XUser("aaa", encoder.encode("aaa"), List.of("ADMIN", "USER"));
        var bb = new XUser("bbb", encoder.encode("bbb"), List.of("USER"));
        userRepo.save(aa);
        userRepo.save(bb);
        System.out.println("--- INIT SPRING SECURITY");
    }

    

    /*
     * Définir le fournisseur d'authentification. Nous utilisons la version
     * DaoAuthenticationProvider qui récupère les informations à partir du
     * UserDetailsService que nous avons défini avant.
     */
    @Bean
    public AuthenticationProvider myAuthenticationProvider(//
                                                           @Autowired PasswordEncoder encoder, //
                                                           @Autowired UserDetailsService userDetailsService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    /*
     * Définir la politique par défaut pour le cryptage des mots de passe.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}