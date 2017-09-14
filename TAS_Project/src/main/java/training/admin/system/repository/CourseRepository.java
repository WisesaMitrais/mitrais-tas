package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Course;

@Component
public interface CourseRepository extends JpaRepository<Course, Long>{
	Course findByName(String name);
}
