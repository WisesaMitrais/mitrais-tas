package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Office;
import training.admin.system.model.Training;
import training.admin.system.repository.TrainingRepository;

@RestController
@RequestMapping("/training")
public class PeriodController {
	
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
	
	@RequestMapping (value="/create", method=RequestMethod.POST)
	public void add(@RequestBody Training training) {
		trainingRepository.save(training);
	}
	
	@RequestMapping (value="/update",method = RequestMethod.POST)
	public void update (@RequestBody Training trainingParam) {
		Training training = trainingRepository.findOne(trainingParam.getIdTraining());
		training.setTrainingName(trainingParam.getTrainingName());
		training.setStartDate(trainingParam.getStartDate());
		training.setEndDate(trainingParam.getEndDate());
		training.setOpenEnrollment(trainingParam.isOpenEnrollment());
		trainingRepository.save(training);
	}
	
	@RequestMapping (value="/delete", method = RequestMethod.DELETE)
	public void delete (@RequestParam ("id") Long idTraining) {
		trainingRepository.delete(idTraining);
	}
	
	
	@RequestMapping (value="/findOpenEnrollment", method = RequestMethod.GET)
	public List<Training> findOpenEnrollment (){
		return trainingRepository.findByOpenEnrollment(true);
	}
}
