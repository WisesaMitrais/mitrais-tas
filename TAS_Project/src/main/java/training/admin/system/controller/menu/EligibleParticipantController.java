package training.admin.system.controller.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.AddEligibleParticipant;
import training.admin.system.EligibleParticipantData;
import training.admin.system.model.EligibleParticipant;
import training.admin.system.model.Training;
import training.admin.system.model.User;
import training.admin.system.repository.EligibleParticipantRepository;
import training.admin.system.repository.TrainingRepository;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping("/eligibleparticipant")
public class EligibleParticipantController {

	@Autowired 
	EligibleParticipantRepository eligibleParticipantRepository;
	@Autowired
	TrainingRepository trainingRepository;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<EligibleParticipantData> findAll(){
		List <EligibleParticipantData> result = new ArrayList<EligibleParticipantData>(); 
		List <EligibleParticipant> list = eligibleParticipantRepository.findAll();
		for (EligibleParticipant eligibleParticipant:list) {
			result.add(convertEligibleParticipantToEligibleParticipantData(eligibleParticipant));
		}
		return result;
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<EligibleParticipant> findAll(Pageable pageable){
		return eligibleParticipantRepository.findAll(pageable);
	}
	
	@GetMapping ("/findByTraining/{idTraining}")
	public List<EligibleParticipantData> findByTraining (@PathVariable ("idTraining") Long idTraining){
		List <EligibleParticipantData> result = new ArrayList<EligibleParticipantData>(); 
		Training training = trainingRepository.findOne(idTraining);
		List <EligibleParticipant> list = eligibleParticipantRepository.findByTraining(training);
		for (EligibleParticipant eligibleParticipant:list) {
			result.add(convertEligibleParticipantToEligibleParticipantData(eligibleParticipant));
		}
		return result;
	}
	
	@GetMapping ("/findByUser/{idUser}")
	public List<EligibleParticipantData> findByUser (@PathVariable ("idUser") Long idUser){
		List <EligibleParticipantData> result = new ArrayList<EligibleParticipantData>(); 
		User user = userRepository.findOne(idUser);
		List <EligibleParticipant> list = eligibleParticipantRepository.findByUser(user);
		for (EligibleParticipant eligibleParticipant:list) {
			result.add(convertEligibleParticipantToEligibleParticipantData(eligibleParticipant));
		}
		return result;
	}
	
	@PostMapping ("/add")
	public Boolean addEligibleParticipant (@RequestBody AddEligibleParticipant addEligibleParticipant) {
//		System.out.println("ID Training: " + addEligibleParticipant.getIdTraining());
//		System.out.println("ID User    : " + addEligibleParticipant.getIdUser());
		try {
			Training training = trainingRepository.findOne(addEligibleParticipant.getIdTraining());
			for (int i = 0; i< addEligibleParticipant.getIdUser().size(); i++) {
				EligibleParticipant eligibleParticipant = new EligibleParticipant();
				User user = userRepository.findOne(addEligibleParticipant.getIdUser().get(i));
				eligibleParticipant.setTraining(training);
				eligibleParticipant.setUser(user);
				eligibleParticipantRepository.save(eligibleParticipant);	
			}
			return Boolean.TRUE;	
		
		
		}catch (Exception e){
			System.out.println(e);;
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/delete", method = RequestMethod.DELETE)
	public Boolean delete (@PathVariable ("id") Long idEligible) {
		try {
			eligibleParticipantRepository.delete(idEligible);
			return Boolean.TRUE;
		}catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	public EligibleParticipantData convertEligibleParticipantToEligibleParticipantData(EligibleParticipant eligibleParticipant) {
		EligibleParticipantData eligibleParticipantData = new EligibleParticipantData();
		eligibleParticipantData.setIdUser(eligibleParticipant.getUser().getIdUser());
		eligibleParticipantData.setEligibleNumber(eligibleParticipant.getIdEligibleParticipant());
		eligibleParticipantData.setName(eligibleParticipant.getUser().getName());
		return eligibleParticipantData;
	}
}
