package mybootapp.security;

import mybootapp.jpa.dao.repositories.MemberRepository;
import mybootapp.jpa.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    // Assuming you have a PasswordEncoder bean configured in your security configuration
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'adresse e-mail: " + email);
        }

        // Convert Member entity to Spring Security UserDetails
        // Adjust roles and authorities as needed for your application
        return org.springframework.security.core.userdetails.User
                // Use ID as the username field for Spring Security contexts
                .withUsername(String.valueOf(member.getId()))
                // Ensure the password is encoded using the same PasswordEncoder as when storing passwords
                .password(passwordEncoder.encode(member.getPassword()))
                .roles("USER") // Define roles as per your requirements. Prefix ROLE_ is automatically added.
                // For finer-grained control, use authorities() instead of roles() for specific permissions.
                .build();
    }

    // Optional: You might want to override additional methods or create a custom UserDetails class
    // for storing additional properties of the Member entity that could be useful in your application.
}
