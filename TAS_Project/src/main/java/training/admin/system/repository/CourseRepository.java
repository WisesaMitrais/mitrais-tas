package training.admin.system.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Course;

@Component
public interface CourseRepository extends JpaRepository<Course, Long>{
	Course findByName(String name);
	List<Course> findByBccCourse(Boolean bccCourse);
}
