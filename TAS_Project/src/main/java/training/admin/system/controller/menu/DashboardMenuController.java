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

import training.admin.system.ActiveTraining;
import training.admin.system.BccCourse;
import training.admin.system.model.Course;
import training.admin.system.model.Schedule;
import training.admin.system.model.User;
import training.admin.system.repository.CourseRepository;
import training.admin.system.repository.RoomRepository;
import training.admin.system.repository.ScheduleRepository;
import training.admin.system.repository.UserRepository;
import training.admin.system.repository.OfficeRepository;

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
	
	@SuppressWarnings("deprecation")
	@RequestMapping (value="/activeTraining", method = RequestMethod.GET)
	public List<ActiveTraining> getActiveTraining(){
		System.out.println("\nProcessing for ActiveTraining Request");
		System.out.println("---------------------------------");
		
		Date startDay = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDay);
		calendar.add(Calendar.DATE, -1);
		
		startDay.setDate(startDay.getDate()-1);
		startDay.setHours(23); startDay.setMinutes(59); startDay.setSeconds(59);
		System.out.println ("StartDay: " + startDay);
		List<Schedule> schedules = scheduleRepository.findByEndDateAfter(startDay);
		System.out.println("size = " + schedules.size());
		
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
	
	@SuppressWarnings("deprecation")
	@RequestMapping (value="/bccCourse", method = RequestMethod.GET)
	public List<BccCourse> getBccCourse(){
		
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
		
		List<Schedule> schedules = scheduleRepository.findByStartDateAfterAndEndDateBefore(startDateWeek, endDateWeek);
		System.out.println("schedules length = " + schedules.size());
		
		for (Schedule schedule:schedules) {
			System.out.println("schedules id = " + schedule.getIdSchedule());
			Course course = courseRepository.findOne(schedule.getIdCourse());

			if(!course.isBccCourse()) continue;
				
			System.out.println("course = " + course.getName() + " " + course.isBccCourse());
			
			String trainerName = userRepository.findOne(schedule.getIdCourse()).getName();
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
			
			System.out.println("createNew = " +createNew); 
			
			newBccCourse.setTrainer(trainerName);
			for (Integer i=1; i<=5; i++) {
				Integer startCourse = schedule.getStart_date().getDay();
				Integer endCourse = schedule.getEnd_date().getDay();
				String data;
				
				if(i>=startCourse && i<=endCourse) {
					String courseName = courseRepository.findOne(schedule.getIdCourse()).getName();
					String roomName = roomRepository.findOne(schedule.getIdRoom()).getName();
					data = courseName + ", " + roomName;
				} else {
					data = "-";
				}
				
				switch (i) {
				case 1:
					if (!createNew) {
						if (newBccCourse.getMon().compareTo("-")==0) {
							newBccCourse.setMon(data);
						}
					}
					else 
						newBccCourse.setMon(data);
					break;
				case 2:
					if (!createNew) {
						if (newBccCourse.getTue().compareTo("-")==0) {
							newBccCourse.setTue(data);
						}
					}
					else 
						newBccCourse.setTue(data);
					break;
				case 3:
					if (!createNew) {
						if (newBccCourse.getWed().compareTo("-")==0) {
							newBccCourse.setWed(data);
						}
					}
					else 
						newBccCourse.setWed(data);
					break;
				case 4:
					if (!createNew) {
						if (newBccCourse.getThu().compareTo("-")==0) {
							newBccCourse.setThu(data);
						}
					}
					else 
						newBccCourse.setThu(data);
					break;
				case 5:
					if (!createNew) {
						if (newBccCourse.getFri().compareTo("-")==0) {
							newBccCourse.setFri(data);
						}
					}
					else 
						newBccCourse.setFri(data);
					break;
				default:
					break;
				}
				
			}
			if (createNew)
				bccCourses.add(newBccCourse);
			else {
				bccCourses.set(index, newBccCourse);
			}
		}
		
		return bccCourses;
	}
	
	
}

