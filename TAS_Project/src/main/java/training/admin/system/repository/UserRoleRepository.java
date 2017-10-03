package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import training.admin.system.model.Role;
import training.admin.system.model.User;
import training.admin.system.model.UserRole;

@Component
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
	List<UserRole> findByIdUser(Long idUser); 
	UserRole findByIdUserRole (Long IdUserRole);
	List<UserRole> findByIdRole (Long idRole);
}
