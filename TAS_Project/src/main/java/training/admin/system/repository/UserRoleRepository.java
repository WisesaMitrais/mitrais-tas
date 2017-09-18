package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import training.admin.system.model.UserRole;

@Component
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
	List<UserRole> findByIdUser(Long id_user); 
	UserRole findByIdUserRole (Long idUserRole);
	UserRole findByIdRole (Long idRole);
}
