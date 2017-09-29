package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Achievement;
import training.admin.system.model.Course;
import training.admin.system.model.User;

@Component
public interface AchievementRepository extends JpaRepository<Achievement, Long>{
	List <Achievement> findByUserAndCourse(User user, Course course);
	List <Achievement> findByUser(User user);
}
