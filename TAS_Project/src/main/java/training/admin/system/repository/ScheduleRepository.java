package training.admin.system.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import training.admin.system.model.Course;
import training.admin.system.model.Schedule;
import training.admin.system.model.Training;

@Component
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findByStartDateBeforeAndEndDateAfter(Date startDate, Date endDate);
	List<Schedule> findByStartDateAfterAndEndDateBefore(Date startDate, Date endDate);
	List<Schedule> findByEndDateAfterAndTraining(Date today, Training training);
	List<Schedule> findByTraining(Training training);
	
	@Query(value="SELECT COUNT (id_schedule) FROM tb_schedule Where id_course=? AND id_training=?", nativeQuery = true)
	Integer countSchedule(Long idTraining, Long idCourse);
	
	List<Schedule> findByCourseAndTraining(Course Couirse, Training training);
}


