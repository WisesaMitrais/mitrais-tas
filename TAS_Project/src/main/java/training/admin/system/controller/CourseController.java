package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Course;
import training.admin.system.repository.CourseRepository;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	CourseRepository courseRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Course> findAll(){
		return courseRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Course> findAll (Pageable pageable){
		return courseRepository.findAll(pageable);
	}
	
	@RequestMapping(value="/bcc", method = RequestMethod.GET)
	public List<Course> findBcc (){
		return courseRepository.findByBccCourse(true);
	}
	
	@RequestMapping(value="/notBcc", method = RequestMethod.GET)
	public List<Course> findNotBcc (){
		return courseRepository.findByBccCourse(false);
	}
}
