package training.admin.system.controller.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.AchievementData;
import training.admin.system.model.Achievement;
import training.admin.system.model.User;
import training.admin.system.repository.AchievementRepository;
import training.admin.system.repository.OfficeRepository;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping("/achievement")
public class AchievementController {
	
	@Autowired
	AchievementRepository achievementRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OfficeRepository officeRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Achievement> findAll( ) {
		return achievementRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Achievement> findAll(Pageable pageable) {
		return achievementRepository.findAll(pageable);
	}
	
	@GetMapping(value="/findByUser/{idUser}")
	public List<AchievementData> findByUser(@PathVariable Long idUser){
		List <AchievementData> listAchievment = new ArrayList<AchievementData>();
		return listAchievment;
	}
	
	public AchievementData convertAchievementToAchievementData(Achievement achievement){
		AchievementData achievementData = new AchievementData();
		User user = userRepository.findOne(achievement.getIdUser());
		achievementData.setIdEmployee(user.getIdUser());
		achievementData.setEmployeeName(user.getName());
		achievementData.setJobFamily(user.getJobFamilyStream());
		achievementData.setGrade(user.getGrade());
		achievementData.setOffice(officeRepository.findOne(user.getOffice()).getCity());
		
		String beginning = "-";
		List<Achievement> beginningAchievement = achievementRepository.findByIdUserAndIdCourse(achievement.getIdUser(), achievement.getIdCourse());
		if (beginningAchievement.size()<=0){
			
		}
		
		achievementData.setBeginning(beginning);
		achievementData.setLI1("LI1");
		achievementData.setLI2("LI2");
		achievementData.setInt1("Int. 2");
		achievementData.setInt2("Int. 2");
		achievementData.setBW1("BW1");
		achievementData.setCE1("CE1");
		achievementData.setBW2("BW2");
		achievementData.setCE2("CE2");
		achievementData.setPresentationSkill2("Presentation Skills 2");
		return achievementData;
	}
}
