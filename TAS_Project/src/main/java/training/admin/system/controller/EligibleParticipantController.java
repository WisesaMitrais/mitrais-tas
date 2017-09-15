package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.EligibleParticipant;
import training.admin.system.repository.EligibleParticipantRepository;

@RestController
@RequestMapping("/eligibleparticipant")
public class EligibleParticipantController {

	@Autowired 
	EligibleParticipantRepository eligibleParticipantRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<EligibleParticipant> findAll(){
		return eligibleParticipantRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<EligibleParticipant> findAll(Pageable pageable){
		return eligibleParticipantRepository.findAll(pageable);
	}
	
}
