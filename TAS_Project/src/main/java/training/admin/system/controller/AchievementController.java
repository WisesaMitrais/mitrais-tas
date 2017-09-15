package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Achievement;
import training.admin.system.repository.AchievementRepository;

@RestController
@RequestMapping("/achievement")
public class AchievementController {
	
	@Autowired
	AchievementRepository achievementRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Achievement> findAll( ) {
		return achievementRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Achievement> findAll(Pageable pageable) {
		return achievementRepository.findAll(pageable);
	}
}
