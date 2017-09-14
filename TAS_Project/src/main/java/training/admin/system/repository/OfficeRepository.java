package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Office;

@Component
public interface OfficeRepository extends JpaRepository<Office, Long>{
	Office findByName(String name);
}
