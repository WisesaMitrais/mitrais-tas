package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Enrollment;

@Component
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	List<Enrollment> findByIdSchedule(Long idSchedule);
}
