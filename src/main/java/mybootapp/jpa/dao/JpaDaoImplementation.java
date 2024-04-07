package mybootapp.jpa.dao;

import mybootapp.jpa.dao.repositories.CategoryRepository;
import mybootapp.jpa.dao.repositories.MemberRepository;
import mybootapp.jpa.dao.repositories.SortieRepository;
import mybootapp.jpa.model.Category;
import mybootapp.jpa.model.Member;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'interface JpaDao, utilisant Spring Data JPA pour interagir avec la base de données.
 */
@Repository
@Transactional
public class JpaDaoImplementation implements JpaDao {

    @Autowired
    @Qualifier("memberRepository")
    private MemberRepository memberRepository;

    @Autowired
    @Qualifier("categoryRepository")
    private CategoryRepository categoryRepository;

    @Autowired
    @Qualifier("sortieRepository")
    private SortieRepository sortieRepository;

    /**
     * Recherche des sorties basées sur des mots-clés, en ignorant la casse.
     * 
     * @param keyword Le mot-clé pour la recherche.
     * @return Une liste de sorties correspondant aux critères de recherche.
     */
    @Override
    public List<Sortie> searchSortiesParCriteres(String keyword) {
        return sortieRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    // Les autres méthodes suivent le même schéma de documentation. Chaque méthode est annotée avec une 
    // description claire de son rôle, les paramètres acceptés, et ce qu'elle retourne.

    // Exemple de documentation pour une autre méthode:
    /**
     * Récupère toutes les catégories disponibles dans la base de données.
     * 
     * @return Une liste de toutes les catégories.
     */
    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    // Continuez avec des commentaires similaires pour les autres méthodes...

}
