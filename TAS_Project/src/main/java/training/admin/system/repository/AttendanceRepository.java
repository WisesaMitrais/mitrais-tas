package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Attendance;
import training.admin.system.model.Enrollment;

@Component
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	List <Attendance> findByEnrollment(Enrollment enrollment);
}
