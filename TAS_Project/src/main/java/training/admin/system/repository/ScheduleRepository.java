package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Schedule;

@Component
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
