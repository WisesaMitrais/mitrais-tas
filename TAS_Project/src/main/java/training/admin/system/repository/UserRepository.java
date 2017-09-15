package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long>{
	User findByNameIgnoreCase(String name);
	User findByUsernameAndPassword(String username, String password);
}
