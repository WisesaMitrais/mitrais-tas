package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import training.admin.system.Trainer;
import training.admin.system.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long>{
	User findByNameIgnoreCase(String name);
	User findByUsernameAndPassword(String username, String password);
	
	@Query(value="SELECT id_user, name FROM tbm_user u ", nativeQuery = true)
	List <Long> selectAllId();
	
/*	@Query("SELECT u FROM User u, UserRole ur, Role r "
			+ "WHERE u.idUser=ur.User.idUser "
			+ "AND ur.idRole=r.idRole "
			+ "AND r.roleName='Trainer'")
    public List<User> findTrainer();*/
}
