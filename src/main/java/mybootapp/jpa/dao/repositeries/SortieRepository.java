package mybootapp.jpa.dao.repositeries;


import mybootapp.jpa.model.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SortieRepository extends JpaRepository<Sortie, Long> {
    List<Sortie> findAllByDescriptionContainingIgnoreCase(String keyword);
    @Query("SELECT s FROM Sortie s WHERE s.creator.id = :id")
    List<Sortie> findSortiesByCreator(Long id);

    List<Sortie> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nomKeyword, String descriptionKeyword);
}
