package training.admin.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.ActiveTraining;
import training.admin.system.BccCourse;
import training.admin.system.model.Schedule;
import training.admin.system.model.User;
import training.admin.system.repository.CourseRepository;
import training.admin.system.repository.RoomRepository;
import training.admin.system.repository.ScheduleRepository;
import training.admin.system.repository.UserRepository;
import training.admin.system.repository.OfficeRepository;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	OfficeRepository officeRepository;
	
	@RequestMapping (value="/activeTraining", method = RequestMethod.GET)
	public List<ActiveTraining> getActiveTraining(){
		Date now = new Date(System.currentTimeMillis());
		List<Schedule> schedules = scheduleRepository.findByStartDateBeforeAndEndDateAfter(now, now);
	
		List <ActiveTraining> activeTrainings = new ArrayList<ActiveTraining>();
		for (Schedule schedule:schedules) {
			ActiveTraining activeTraining = new ActiveTraining();
			activeTraining.setCourseName(courseRepository.findOne(schedule.getIdCourse()).getName());
			activeTraining.setMainTrainer(userRepository.findOne(schedule.getIdMainTrainer()).getName());
			
			if (schedule.getIdBackupTrainer() != null) {
					activeTraining.setBackupTrainer(userRepository.findOne(schedule.getIdBackupTrainer()).getName());
			}
			
			Date startDate = schedule.getStart_date();
			activeTraining.setStartDate(new SimpleDateFormat("dd-MMM-yyyy").format(startDate));
			
			Date endDate = schedule.getEnd_date();
			activeTraining.setEndDate(new SimpleDateFormat("dd-MMM-yyyy").format(endDate));
			
			Long idRoom = schedule.getIdRoom();
			Long idOffice = roomRepository.findOne(idRoom).getIdOffice();
			activeTraining.setOffice(officeRepository.findOne(idOffice).getCity());
			
			activeTrainings.add(activeTraining);
		}
		
		return activeTrainings;
	}
	
	@RequestMapping (value="/bccCourse", method = RequestMethod.GET)
	public List<BccCourse> getBccCourse(){
		List<BccCourse> bccCourses = new ArrayList<BccCourse>();
		
		// Logic is missing ...
		
		return bccCourses;
	}
	
	
}

