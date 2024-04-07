package mybootapp.jpa.dao;



import mybootapp.jpa.model.Category;
import mybootapp.jpa.model.Member;
import mybootapp.jpa.model.Sortie;

import java.util.List;


public interface JpaDao {

    List<Category> findAllCategories();
    List<Sortie> findSortiesByCategory(Long categoryId);
    Sortie findSortieDetails(Long sortieId);
    List<Sortie> searchSortiesParCriteres(String keyword);
    Category findCategorieDetails(Long sortieId);
    List<Member> findAllMembers();
    List<Sortie> findAllSorties();
    Member findMemberById(Long memberId);
    Member findMemberWithSorties(Long memberId);

    Sortie saveSortie(Sortie sortie);
    Category saveCategory(Category category);
    void deleteSortie(Long sortieId);

    Member saveMember(Member member);
}
