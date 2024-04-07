package mybootapp;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Category;
import mybootapp.jpa.model.Member;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@EnableJpaRepositories(basePackages = "mybootapp")
@EntityScan(basePackages = "mybootapp")
@SpringBootApplication
public class Starter implements WebMvcConfigurer {

	@Autowired
	private JpaDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		registry.addFormatter(new DateFormatter(formatter.toString()));
	}

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
	@Bean("messageSource")
	public MessageSource messageSource() {
		var r = new ReloadableResourceBundleMessageSource();
		r.setBasenames("classpath:product", "classpath:messages");
		return r;
	}
	@Bean
	public CommandLineRunner initData() {
		return args -> {
			// Création des catégories
			Category category1 = new Category("Escalade de roche");
			Category category2 = new Category("Badminton");
			Category category3 = new Category("Randonnée");
			dao.saveCategory(category1);
			dao.saveCategory(category2);
			dao.saveCategory(category3);

			// Création des membres
			Member member1 = new Member("Vincent", "Goumba", null, "test@gmail.com", passwordEncoder.encode("jeanlucmassat"));
			Member member2 = new Member("Marie", "Durand", null, "marie@gmail.com", passwordEncoder.encode("mdpMarie"));
			Member member3 = new Member("Jean", "Dupont", null, "jean@gmail.com", passwordEncoder.encode("mdpJean"));
			dao.saveMember(member1);
			dao.saveMember(member2);
			dao.saveMember(member3);

			// Création des sorties
			// Création des dates
			Date dateSortie1 = new Date();
			Date dateSortie2 = new Date();
			Date dateSortie3 = new Date();

// Création des sorties avec les dates
			Sortie sortie1 = new Sortie(0, "Badminton en roller", "Pas très très évident mais plaisant avec le temps...", "https://badmintonenroller.com", member1, dateSortie1, category2);
			Sortie sortie2 = new Sortie(0, "Escalade en salle", "Pour les débutants et les confirmés, venez vous amuser !", "https://escaladeensalle.com", member2, dateSortie2, category1);
			Sortie sortie3 = new Sortie(0, "Randonnée en montagne", "Profitez de la nature et de l'air frais en montagne !", "https://randonnee.com", member3, dateSortie3, category3);
			dao.saveSortie(sortie1);
			dao.saveSortie(sortie2);
			dao.saveSortie(sortie3);

			// Affichage des sorties
			for (Sortie sortie : dao.findAllSorties()) {
				System.out.println(sortie.toString());
			}
		};
	}
}
