package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.DetailAssesment;
import training.admin.system.repository.DetailAssesmentRepository;

@RestController
@RequestMapping("/detailassesment")
public class DetailAssesmentController {
	
	@Autowired
	DetailAssesmentRepository detailAssesmentRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<DetailAssesment> findAll(){
		return detailAssesmentRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<DetailAssesment> findAll(Pageable pageable){
		return detailAssesmentRepository.findAll(pageable);
	}

}
