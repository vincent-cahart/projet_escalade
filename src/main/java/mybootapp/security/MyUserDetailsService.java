package mybootapp.security;

import mybootapp.jpa.dao.repositeries.MemberRepository;
import mybootapp.jpa.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'adresse e-mail: " + email);
        }

        // Créez un UserDetails basé sur les informations du Member
        return org.springframework.security.core.userdetails.User.withUsername(String.valueOf(member.getId()))
                .password(member.getPassword())
                .roles("USER") // Définissez les rôles selon vos besoins
                .build();
    }


}
