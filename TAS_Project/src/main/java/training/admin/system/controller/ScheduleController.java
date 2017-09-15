package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Schedule;
import training.admin.system.repository.ScheduleRepository;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Schedule> findAll(){
		return scheduleRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Schedule> findAll(Pageable pageable){
		return scheduleRepository.findAll(pageable);
	}
}
