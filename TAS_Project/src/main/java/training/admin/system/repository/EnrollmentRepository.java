package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Enrollment;

@Component
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

}
