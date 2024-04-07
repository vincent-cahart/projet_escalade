package mybootapp.jpa.dao;

import mybootapp.jpa.dao.repositeries.CategoryRepository;
import mybootapp.jpa.dao.repositeries.MemberRepository;
import mybootapp.jpa.dao.repositeries.SortieRepository;
import mybootapp.jpa.model.Category;
import mybootapp.jpa.model.Member;
import mybootapp.jpa.model.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Sortie> searchSortiesParCriteres(String keyword) {
        return sortieRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }



    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Sortie> findSortiesByCategory(Long categoryId) {
        // Récupérer la catégorie spécifiée
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        List<Sortie> sorties = sortieRepository.findAll();
        List<Sortie> sortiesByCategory = new ArrayList<>();

        for (Sortie sortie : sorties) {
            if (sortie.getCategory().equals(category)) {
                sortiesByCategory.add(sortie);
            }
        }
        return sortiesByCategory;
    }

    @Override
    public Category findCategorieDetails(Long sortieId) {
        return categoryRepository.findById(sortieId).orElse(null);
    }
    @Override
    public Sortie findSortieDetails(Long sortieId) {
        return sortieRepository.findById(sortieId).orElse(null);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public List<Sortie> findAllSorties() {
        return sortieRepository.findAll();
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Member findMemberWithSorties(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Sortie saveSortie(Sortie sortie) {
        return sortieRepository.save(sortie);
    }

    @Override
    public void deleteSortie(Long sortieId) {
        sortieRepository.deleteById(sortieId);
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
