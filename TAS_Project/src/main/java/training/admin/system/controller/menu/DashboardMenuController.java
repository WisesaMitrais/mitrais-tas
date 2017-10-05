package training.admin.system.controller.menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import training.admin.system.ActiveTraining;
import training.admin.system.BccCourse;
import training.admin.system.model.Course;
import training.admin.system.model.Office;
import training.admin.system.model.Schedule;
import training.admin.system.model.Training;
import training.admin.system.repository.CourseRepository;
import training.admin.system.repository.OfficeRepository;
import training.admin.system.repository.RoomRepository;
import training.admin.system.repository.ScheduleRepository;
import training.admin.system.repository.TrainingRepository;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping("/dashboard")
public class DashboardMenuController {

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
	@Autowired
	TrainingRepository trainingRepository;
	
	@SuppressWarnings("deprecation")
	@RequestMapping (value="/activeTraining", method = RequestMethod.GET)
	public List<ActiveTraining> getActiveTraining(){
		System.out.println("\nProcessing for ActiveTraining Request");
		System.out.println("---------------------------------");
		System.out.println(System.currentTimeMillis());

		List <ActiveTraining> activeTrainings = new ArrayList<ActiveTraining>();
		
		Date today = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, -1);
		
		today.setDate(today.getDate()-1);
		today.setHours(23); today.setMinutes(59); today.setSeconds(59);
		
		List<Training> trainings = trainingRepository.findByActive(true);
		for (Training training:trainings) {
	
			List<Schedule> schedules = scheduleRepository.findByEndDateAfterAndTraining(today, training);

			for (Schedule schedule:schedules) {
				ActiveTraining activeTraining = new ActiveTraining();
				activeTraining.setCourseName(schedule.getCourse().getName() + " #" +schedule.getScheduleNumber());
				activeTraining.setMainTrainer(schedule.getMainTrainer().getName());
				
				if (schedule.getIdBackupTrainer() != null) {
						activeTraining.setBackupTrainer(userRepository.findOne(schedule.getIdBackupTrainer()).getName());
				}
				 
				Date startDate = schedule.getStartDate();
				activeTraining.setStartDate(new SimpleDateFormat("d MMMM yyyy").format(startDate));
				
				Date endDate = schedule.getEndDate();
				activeTraining.setEndDate(new SimpleDateFormat("d MMMM yyyy").format(endDate));
				
				Office office = schedule.getRoom().getOffice();
				activeTraining.setOffice(office.getCity());
				
				activeTrainings.add(activeTraining);
			}
		}
		return activeTrainings;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping (value="/bccCourse", method = RequestMethod.GET)
	public List<BccCourse> getBccCourse() throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("\nProcessing for BCC Course Request");
		System.out.println("---------------------------------");
		
		List<BccCourse> bccCourses = new ArrayList<BccCourse>();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.add(Calendar.DATE, 1);
		Date startDateWeek = cal.getTime();
		System.out.println("StartDate = " + startDateWeek.toString());
		
		cal.add(Calendar.DATE, 4);
		Date endDateWeek = cal.getTime();
		System.out.println("EndDate = " + endDateWeek.toString());
		
		List<Schedule> schedules = scheduleRepository.findByStartDateBeforeAndEndDateAfter(endDateWeek, startDateWeek);
		System.out.println("schedules length = " + schedules.size());
		

		System.out.println(objectMapper.writeValueAsString(schedules));
		
		for (Schedule schedule:schedules) {
			System.out.println("schedules id = " + schedule.getIdSchedule());
			Course course = schedule.getCourse();

			if(!course.isBccCourse()) continue;
				
			System.out.println("course = " + course.getName() + " " + course.isBccCourse());
			
			String trainerName = schedule.getMainTrainer().getName();
			BccCourse newBccCourse = new BccCourse();
			
			Boolean createNew = true;
			Integer iter = 0;
			Integer index = -1;
			for (BccCourse bccCourse:bccCourses) {
				if (bccCourse.getTrainer().compareTo(trainerName)==0) {
					index = iter;
					createNew = false;
					newBccCourse = bccCourse;
					break;
				}
				iter++;
			}
			
			System.out.println("createNew = " +createNew +" " + objectMapper.writeValueAsString(newBccCourse)); 
			
			newBccCourse.setTrainer(trainerName);
			for (Integer i=1; i<=5; i++) {
				Integer day = Integer.parseInt(schedule.getDay());
				String data;
				
				if(i==day) {
					String courseName = schedule.getCourse().getName() + " #" +schedule.getScheduleNumber();
					String roomName = schedule.getRoom().getName();
					data = courseName + ", " + roomName;
				} else {
					data = "-";
				}
				
				switch (i) {
				case 1:
					if (!createNew) {
						if (newBccCourse.getMon().compareTo("-")==0) 
							newBccCourse.setMon(data);
						
					}
					else 
						newBccCourse.setMon(data);
					break;
				case 2:
					if (!createNew) {
						if (newBccCourse.getTue().compareTo("-")==0) 
							newBccCourse.setTue(data);
						
					}
					else 
						newBccCourse.setTue(data);
					break;
				case 3:
					if (!createNew) {
						if (newBccCourse.getWed().compareTo("-")==0) 
							newBccCourse.setWed(data);
						
					}
					else 
						newBccCourse.setWed(data);
					break;
				case 4:
					if (!createNew) {
						if (newBccCourse.getThu().compareTo("-")==0) 
							newBccCourse.setThu(data);
						
					}
					else 
						newBccCourse.setThu(data);
					break;
				case 5:
					if (!createNew) {
						if (newBccCourse.getFri().compareTo("-")==0) 
							newBccCourse.setFri(data);
						
					}
					else 
						newBccCourse.setFri(data);
					break;
				default:
					break;
				}
				
			}

			System.out.println("createNew = " +createNew +" " + objectMapper.writeValueAsString(newBccCourse)); 
			if (createNew)
				bccCourses.add(newBccCourse);
			else {
				bccCourses.set(index, newBccCourse);
			}
		}
		
		return bccCourses;
	}
	
	
}

