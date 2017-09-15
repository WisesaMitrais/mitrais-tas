package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.UserRole;

@Component
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

}
