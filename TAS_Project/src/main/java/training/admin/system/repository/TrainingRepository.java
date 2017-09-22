package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Training;

@Component
public interface TrainingRepository extends JpaRepository<Training, Long>{
	List<Training> findByOpenEnrollment(Boolean openEnrollment);
	List<Training> findByActive(Boolean active);
}
