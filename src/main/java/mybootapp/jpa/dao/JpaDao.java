package mybootapp.jpa.dao;

import mybootapp.jpa.model.Category;
import mybootapp.jpa.model.Member;
import mybootapp.jpa.model.Sortie;
import java.util.List;

/**
 * Interface définissant les opérations DAO (Data Access Object) pour accéder et manipuler les données
 * des catégories, membres, et sorties.
 */
public interface JpaDao {

    /**
     * Trouve toutes les catégories disponibles.
     * 
     * @return Une liste de toutes les catégories.
     */
    List<Category> findAllCategories();

    /**
     * Trouve toutes les sorties associées à une catégorie spécifique.
     * 
     * @param categoryId L'identifiant de la catégorie.
     * @return Une liste des sorties associées à la catégorie.
     */
    List<Sortie> findSortiesByCategory(Long categoryId);

    /**
     * Trouve les détails d'une sortie spécifique.
     * 
     * @param sortieId L'identifiant de la sortie.
     * @return Les détails de la sortie spécifiée.
     */
    Sortie findSortieDetails(Long sortieId);

    /**
     * Recherche des sorties basées sur des critères de recherche spécifiques.
     * 
     * @param keyword Le mot-clé de recherche.
     * @return Une liste des sorties correspondant aux critères de recherche.
     */
    List<Sortie> searchSortiesParCriteres(String keyword);

    /**
     * Trouve les détails d'une catégorie spécifique.
     * 
     * @param categoryId L'identifiant de la catégorie.
     * @return Les détails de la catégorie spécifiée.
     */
    Category findCategorieDetails(Long categoryId);

    /**
     * Trouve tous les membres.
     * 
     * @return Une liste de tous les membres.
     */
    List<Member> findAllMembers();

    /**
     * Trouve toutes les sorties.
     * 
     * @return Une liste de toutes les sorties.
     */
    List<Sortie> findAllSorties();

    /**
     * Trouve un membre par son identifiant.
     * 
     * @param memberId L'identifiant du membre.
     * @return Le membre correspondant à l'identifiant.
     */
    Member findMemberById(Long memberId);

    /**
     * Trouve un membre et ses sorties associées par son identifiant.
     * 
     * @param memberId L'identifiant du membre.
     * @return Le membre avec ses sorties associées.
     */
    Member findMemberWithSorties(Long memberId);

    /**
     * Sauvegarde une sortie dans la base de données.
     * 
     * @param sortie L'entité sortie à sauvegarder.
     * @return La sortie sauvegardée.
     */
    Sortie saveSortie(Sortie sortie);

    /**
     * Sauvegarde une catégorie dans la base de données.
     * 
     * @param category L'entité catégorie à sauvegarder.
     * @return La catégorie sauvegardée.
     */
    Category saveCategory(Category category);

    /**
     * Supprime une sortie de la base de données.
     * 
     * @param sortieId L'identifiant de la sortie à supprimer.
     */
    void deleteSortie(Long sortieId);

    /**
     * Sauvegarde un membre dans la base de données.
     * 
     * @param member L'entité membre à sauvegarder.
     * @return Le membre sauvegardé.
     */
    Member saveMember(Member member);
}
