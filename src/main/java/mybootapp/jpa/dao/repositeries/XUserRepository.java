package mybootapp.jpa.dao.repositeries;

import mybootapp.jpa.model.XUser;
import org.springframework.data.jpa.repository.JpaRepository;




public interface XUserRepository extends JpaRepository<XUser, String> {

}