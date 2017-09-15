package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Training;
import training.admin.system.repository.TrainingRepository;

@RestController
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	TrainingRepository trainingRepository;
	
	@RequestMapping (value="/all", method=RequestMethod.GET)
	public List<Training> findAll(){
		return trainingRepository.findAll();
	}
	
	@RequestMapping (value="/allPage", method=RequestMethod.GET)
	public Page<Training> findAll(Pageable pageable){
		return trainingRepository.findAll(pageable);
	}
	
}
