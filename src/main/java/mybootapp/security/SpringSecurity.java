@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurity {

    @Autowired
    XUserRepository userRepo;

    // Customise la configuration de sécurité web pour ignorer certains types de requêtes.
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers("/webjars/**");
        };
    }

    // Configure la chaîne de filtres de sécurité pour définir les règles d'authentification et d'autorisation.
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] anonymousRequests = { "/", "/webjars/**"};
        String[] connectedRequests = {"/profil/**", "/sortie/modifier**"};

        http.authorizeHttpRequests(config -> {
            config.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll();
            config.requestMatchers(anonymousRequests).permitAll();
            config.requestMatchers(connectedRequests).authenticated();
            config.anyRequest().permitAll();
        });

        // Configure le formulaire de connexion et de déconnexion.
        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
        http.logout(config -> {
            config.permitAll();
            config.logoutSuccessUrl("/");
        });

        // Configure CSRF.
        http.csrf(config -> {
            config.ignoringRequestMatchers(anonymousRequests);
        });

        return http.build();
    }

    // Initialise la base de données avec des utilisateurs pour des tests ou un démarrage rapide de l'application.
    @PostConstruct
    public void init() {
        var encoder = passwordEncoder();
        var aa = new XUser("aaa", encoder.encode("aaa"), List.of("ADMIN", "USER"));
        var bb = new XUser("bbb", encoder.encode("bbb"), List.of("USER"));
        userRepo.save(aa);
        userRepo.save(bb);
        System.out.println("--- INIT SPRING SECURITY");
    }

    // Configure le fournisseur d'authentification pour utiliser le service UserDetailsService et le cryptage de mot de passe.
    @Bean
    public AuthenticationProvider myAuthenticationProvider(
            @Autowired PasswordEncoder encoder, 
            @Autowired UserDetailsService userDetailsService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    // Définit la méthode de cryptage des mots de passe.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
