package training.admin.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.ActiveTraining;
import training.admin.system.model.Schedule;
import training.admin.system.model.Training;
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
	
	@RequestMapping (value="/create", method=RequestMethod.POST)
	public void add(@RequestBody Schedule schedule) {
		scheduleRepository.save(schedule);
	}
	
	@RequestMapping (value="/update",method = RequestMethod.POST)
	public void update (@RequestBody Schedule scheduleParam) {
		Schedule schedule= scheduleRepository.findOne(scheduleParam.getIdSchedule());
		schedule.setIdTraining(scheduleParam.getIdTraining());
		schedule.setIdRoom(scheduleParam.getIdRoom());
		schedule.setStart_date(scheduleParam.getStart_date());
		schedule.setEnd_date(scheduleParam.getEnd_date());
		schedule.setCapacity(schedule.getCapacity());
		schedule.setIdCourse(scheduleParam.getIdCourse());
		scheduleRepository.save(schedule);
	}
	
	@RequestMapping (value="/delete", method = RequestMethod.GET)
	public void delete (@RequestParam ("id") Long idTraining) {
		scheduleRepository.delete(idTraining);
	}
	
	
}
