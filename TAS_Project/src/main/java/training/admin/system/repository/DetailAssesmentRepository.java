package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.DetailAssesment;

@Component
public interface DetailAssesmentRepository extends JpaRepository<DetailAssesment, Long>{
	
}
