package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Assesment;
import training.admin.system.model.User;
import training.admin.system.repository.AssesmentRepository;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping ("/assesment")
public class AssesmentController {
	
	@Autowired
	private AssesmentRepository assesmentRepository;
	
	@RequestMapping (value="/all", method=RequestMethod.GET)
	public List<Assesment> findAll(){
		return assesmentRepository.findAll();
	}
	
	@RequestMapping (value="/allPage", method=RequestMethod.GET)
	public Page<Assesment> findAll(Pageable pageable){
		return assesmentRepository.findAll(pageable);
	}
	
	@RequestMapping (value="/create", method=RequestMethod.POST)
	public String add(@RequestBody Assesment assesment) {
		assesmentRepository.save(assesment);
		return "Test";
	}
	
}
