package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Assessment;
import training.admin.system.model.Enrollment;

@Component
public interface AssesmentRepository extends JpaRepository<Assessment, Long>{

	Assessment findByEnrollment(Enrollment enrollment);
}
