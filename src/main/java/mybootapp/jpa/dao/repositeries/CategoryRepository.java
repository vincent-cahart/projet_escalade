package mybootapp.jpa.dao.repositeries;


import mybootapp.jpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}