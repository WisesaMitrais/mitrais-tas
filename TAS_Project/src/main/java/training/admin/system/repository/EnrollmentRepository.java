package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Enrollment;
import training.admin.system.model.Schedule;
import training.admin.system.model.User;

@Component
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	List <Enrollment> findBySchedule(Schedule idSchedule);
	List <Enrollment> findByUser (User idUser);
}
