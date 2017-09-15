package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Attendance;

@Component
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
