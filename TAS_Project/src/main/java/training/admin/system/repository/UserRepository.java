package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import training.admin.system.Trainer;
import training.admin.system.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long>{
	User findByNameIgnoreCase(String name);
	User findByUsernameAndPassword(String username, String password);
		
	@Query(value="SELECT id_user, name FROM tbm_user u "
			+ "LEFT JOIN tr_user_role ur ON u.id_user=ur.id_user "
			+ "LEFT JOIN tbm_role r ON r.role_name='Trainer'", nativeQuery = true)
	List <Trainer> findTrainer();
	
	@Query(value="SELECT id_user, name FROM tbm_user u ", nativeQuery = true)
	List <Long> selectAllId();
}
