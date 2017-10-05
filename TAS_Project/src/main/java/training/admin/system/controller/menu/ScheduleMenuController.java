package training.admin.system.controller.menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.AddSchedule;
import training.admin.system.RepeatData;
import training.admin.system.ScheduleData;
import training.admin.system.model.Assessment;
import training.admin.system.model.Course;
import training.admin.system.model.Enrollment;
import training.admin.system.model.Room;
import training.admin.system.model.Schedule;
import training.admin.system.model.Training;
import training.admin.system.model.User;
import training.admin.system.repository.AssesmentRepository;
import training.admin.system.repository.CourseRepository;
import training.admin.system.repository.EnrollmentRepository;
import training.admin.system.repository.OfficeRepository;
import training.admin.system.repository.RoomRepository;
import training.admin.system.repository.ScheduleRepository;
import training.admin.system.repository.TrainingRepository;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping("/schedule")
public class ScheduleMenuController {
	
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
	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	AssesmentRepository assessmentRepository;
	
	@GetMapping("/all")
	public List<ScheduleData> findAll(){
		List<ScheduleData> schedulesData = new ArrayList<ScheduleData>(); 
		List<Schedule> schedules= scheduleRepository.findAll();
		for (Schedule schedule:schedules) {
			schedulesData.add(ConvertSchedulesToScheduleData(schedule, false));	
		}
		return schedulesData;
	}
	
	@GetMapping("/{id}")
	public Object findOne(@PathVariable ("id") Long idSchedule){
		try {
			return ConvertSchedulesToScheduleData(scheduleRepository.findOne(idSchedule), false);
		}
		catch (Exception exp){
			System.out.println(exp);
			return false;
		}
	}
	
	@PostMapping ("/create")
	public Boolean create(@RequestBody AddSchedule newSchedule) {
	
		try {
			Course course = courseRepository.findOne(newSchedule.getIdCourse());
			Room room = roomRepository.findOne(newSchedule.getIdRoom());
			User mainTrainer= userRepository.findOne(newSchedule.getIdMainTrainer()); 
			Training training = trainingRepository.findOne(newSchedule.getIdTraining());
			Integer number = scheduleRepository.findByCourseAndTraining(course, training).size() + 1;
			Schedule schedule = new Schedule();
			schedule.setCapacity(newSchedule.getCapacity());
			schedule.setStartDate(newSchedule.getStartDate());
			schedule.setEndDate(newSchedule.getEndDate());
			schedule.setTraining(training);
			schedule.setIdBackupTrainer(newSchedule.getIdBackupTrainer());
			schedule.setPeriodic(newSchedule.getPeriodic());
			if (newSchedule.getPeriodic()) {
				schedule.setDay(newSchedule.getDay().toString());
				schedule.setHour(newSchedule.getHour());
			} else {
				schedule.setHour(newSchedule.getHour());
			}
			schedule.setCourse(course);
			schedule.setRoom(room);
			schedule.setMainTrainer(mainTrainer);
			schedule.setScheduleNumber(number);
			schedule.setCreatedBy(Long.parseLong(newSchedule.getCreatedBy()));
			schedule.setUpdatedBy(Long.parseLong(newSchedule.getCreatedBy()));
			Date today = new Date(System.currentTimeMillis());
			schedule.setCreatedAt(today);
			schedule.setUpdatedAt(today);
			scheduleRepository.save(schedule); 
			return Boolean.TRUE;
		}catch (Exception e){
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	@GetMapping("/{id}/edit")
	public Object edit (@PathVariable ("id") Long idSchedule){
		try {
			return ConvertSchedulesToScheduleData(scheduleRepository.findOne(idSchedule), true);
		}
		catch (Exception exp){
			System.out.println(exp);
			return false;
		}
	}
	@RequestMapping (value="/{id}/update",method = RequestMethod.POST)
	public Boolean update (@RequestBody AddSchedule newSchedule,
						@PathVariable ("id") Long idSchedule) {
		try {
			Course course = courseRepository.findOne(newSchedule.getIdCourse());
			Room room = roomRepository.findOne(newSchedule.getIdRoom());
			User mainTrainer= userRepository.findOne(newSchedule.getIdMainTrainer()); 
			Training training = trainingRepository.findOne(newSchedule.getIdTraining());		
			Schedule schedule = scheduleRepository.findOne(idSchedule);
			schedule.setCapacity(newSchedule.getCapacity());
			schedule.setStartDate(newSchedule.getStartDate());
			schedule.setEndDate(newSchedule.getEndDate());
			schedule.setTraining(training);
			schedule.setIdBackupTrainer(newSchedule.getIdBackupTrainer());
			schedule.setPeriodic(newSchedule.getPeriodic());
			if (newSchedule.getPeriodic()) {
				schedule.setDay(newSchedule.getDay().toString());
				schedule.setHour(newSchedule.getHour());
			} else {
				schedule.setHour(newSchedule.getHour());
			}
			schedule.setCourse(course);
			schedule.setRoom(room);
			schedule.setMainTrainer(mainTrainer);
			
			schedule.setUpdatedBy(Long.parseLong(newSchedule.getCreatedBy()));
			Date now = new Date(System.currentTimeMillis());
			schedule.setUpdatedAt(now);
			scheduleRepository.save(schedule);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/delete", method = RequestMethod.DELETE)
	public Boolean delete (@PathVariable ("id") Long idSchedule) {
		try {
			scheduleRepository.delete(idSchedule);
			return Boolean.TRUE;
		}catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	@GetMapping (value="/findByTraining/{idTraining}")
	public List <ScheduleData> findByTraining(@PathVariable Long idTraining){
		List <ScheduleData> scheduleDatas = new ArrayList<ScheduleData>();
		Training training = trainingRepository.findOne(idTraining);
		List <Schedule> schedules = scheduleRepository.findByTraining(training);
		for (Schedule schedule:schedules) {
			scheduleDatas.add(ConvertSchedulesToScheduleData(schedule, false));
		}
		return scheduleDatas;
	}
	
	@GetMapping (value="/findByTrainer/{idTrainer}")
	public List <ScheduleData> findByTrainer(@PathVariable Long idTrainer){
		List <ScheduleData> scheduleDatas = new ArrayList<ScheduleData>();
		User trainer = userRepository.findOne(idTrainer);
		List <Schedule> schedules = scheduleRepository.findByMainTrainerOrIdBackupTrainer(trainer, trainer.getIdUser());
		for (Schedule schedule:schedules) {
			scheduleDatas.add(ConvertSchedulesToScheduleData(schedule, false));
		}
		return scheduleDatas;
	}
	
	@GetMapping (value = "/findRepeatByUser/{idUser}")
	public List<RepeatData> findRepeat (@PathVariable Long idUser){
		List<RepeatData> repeatDatas = new ArrayList<RepeatData>();
		
		User user = userRepository.findOne(idUser);
		List<Enrollment> enrollments = enrollmentRepository.findByUser(user);
		for (Enrollment enrollment:enrollments) {
			RepeatData repeatData = new RepeatData();
			Schedule schedule = enrollment.getSchedule();
			repeatData.setTrainingName(schedule.getTraining().getTrainingName());
			repeatData.setCourseName(schedule.getCourse().getName());
			repeatData.setStartDate(new SimpleDateFormat("d MMMM yyyy").format(schedule.getStartDate()));
			repeatData.setEndDate(new SimpleDateFormat("d MMMM yyyy").format(schedule.getEndDate()));
			
			Assessment assessment = assessmentRepository.findByEnrollment(enrollment);
			if(assessment==null)
				repeatData.setStatus("In Progress");
			else
				repeatData.setStatus(assessment.getStatus());
			
			repeatDatas.add(repeatData);
		}
		
		return repeatDatas;
	}
	
	@GetMapping (value="/count/{idTraining}/{idCourse}")
	public Integer countShcedule (@PathVariable ("idTraining") Long idTraining,
									@PathVariable ("idCourse") Long idCourse){
		return scheduleRepository.countSchedule(idTraining, idCourse);
	}
	
	
	
	
		
	private ScheduleData ConvertSchedulesToScheduleData(Schedule schedule, Boolean edit) {
		ScheduleData scheduleData = new ScheduleData(); 
		scheduleData.setIdSchedule(schedule.getIdSchedule());
		
		scheduleData.setName(schedule.getCourse().getName());
		if (!edit) scheduleData.setName(scheduleData.getName() + " #" + schedule.getScheduleNumber());
		
		User mainTrainer =schedule.getMainTrainer();
		scheduleData.set_MainTrainer(mainTrainer.getIdUser());
		scheduleData.setMainTrainer(mainTrainer.getName());
		
		User backupTrainer = userRepository.findOne(schedule.getIdBackupTrainer());
		if(backupTrainer!=null) {
			scheduleData.set_BackupTrainer(backupTrainer.getIdUser());
			scheduleData.setBackupTrainer(backupTrainer.getName());
		} else {
			scheduleData.setBackupTrainer("-");
		}
		
		Room room = schedule.getRoom();
		scheduleData.set_Room(room.getIdRoom());
		scheduleData.setRoom(room.getName() + " - " + room.getDetail());
		
		scheduleData.setDay(schedule.getDay() + " " + schedule.getHour());
		
		Date startDate = schedule.getStartDate();
		scheduleData.setStartTime(new SimpleDateFormat("d MMMM yyyy").format(startDate));
		scheduleData.set_startDate(startDate);
		
		Date endDate = schedule.getEndDate();
		scheduleData.setEndTime(new SimpleDateFormat("d MMMM yyyy").format(endDate));
		scheduleData.set_endDate(endDate);
		
		
		scheduleData.setCapacity(schedule.getCapacity());
		Schedule tmpSchedule = scheduleRepository.findOne(schedule.getIdSchedule());
		scheduleData.setParticipantNumber(enrollmentRepository.findBySchedule(tmpSchedule).size());
		
		if (schedule.getPeriodic())
			scheduleData.setScheduleType("Periodic");
		else
			scheduleData.setScheduleType("Fixed");
		 
		scheduleData.setPeriodic(schedule.getPeriodic());
		
		String day = "";
		
		if(schedule.getPeriodic()) {
			switch (schedule.getDay()) {
			case "1":
				day = "Monday";
				break;
			case "2":
				day = "Tuesday";
				break;
			case "3":
				day = "Wednesday";
				break;
			case "4":
				day = "Thursday";
				break;
			case "5":
				day = "Friday";
				break;
			case "6":
				day = "Saturday";
				break;
			case "7":
				day = "Sunday";
				break;
	
			default:
				break;
			}
		}
		scheduleData.setDay(day);
		scheduleData.setHour(schedule.getHour());
		
		scheduleData.set_Course(schedule.getCourse().getIdCourse());
		scheduleData.set_Training(schedule.getTraining().getIdTraining());
		
		scheduleData.setCreatedBy(userRepository.findOne(schedule.getCreatedBy()).getName());
		scheduleData.setUpdatedBy(userRepository.findOne(schedule.getUpdatedBy()).getName());
		
		Date createdAt = scheduleRepository.findOne(schedule.getIdSchedule()).getCreatedAt();
		Date updatedAt = scheduleRepository.findOne(schedule.getIdSchedule()).getUpdatedAt();
		scheduleData.setCreatedAt(new SimpleDateFormat("d MMMM yyyy HH:mm:ss").format(createdAt));
		scheduleData.setUpdatedAt(new SimpleDateFormat("d MMMM yyyy HH:mm:ss").format(updatedAt));
		
		scheduleData.setCity(schedule.getRoom().getOffice().getCity());
		return scheduleData;
	}
}
