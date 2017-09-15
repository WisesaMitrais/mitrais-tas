package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Enrollment;
import training.admin.system.repository.EnrollmentRepository;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Enrollment> findAll(){
		return enrollmentRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Enrollment> findAll(Pageable pageable){
		return enrollmentRepository.findAll(pageable);
	}
}
