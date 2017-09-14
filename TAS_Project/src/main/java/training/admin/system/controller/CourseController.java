package training.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Course;
import training.admin.system.repository.CourseRepository;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	CourseRepository courseRepository;
	
	@RequestMapping("/all")
	public Page<Course> findByName(Pageable pageable){
		return courseRepository.findAll(pageable);
	}
}
