package training.admin.system.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Schedule;

@Component
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findByStartDateBeforeAndEndDateAfter(Date startDate, Date endDate);
	List<Schedule> findByStartDateAfterAndEndDateBefore(Date startDate, Date endDate);
}


