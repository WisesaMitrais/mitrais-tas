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

import training.admin.system.PeriodData;
import training.admin.system.ScheduleData;
import training.admin.system.model.Room;
import training.admin.system.model.Schedule;
import training.admin.system.model.Training;
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
	
	@GetMapping("/all")
	public List<ScheduleData> findAll(){
		List<ScheduleData> schedulesData = new ArrayList<ScheduleData>(); 
		List<Schedule> schedules= scheduleRepository.findAll();
		for (Schedule schedule:schedules) {
			schedulesData.add(ConvertSchedulesToScheduleData(schedule));	
		}
		return schedulesData;
	}
	
	@GetMapping("/{id}")
	public ScheduleData findOne(@PathVariable ("id") Long idSchedule){
		Schedule schedule = scheduleRepository.findOne(idSchedule);
		return ConvertSchedulesToScheduleData(schedule);	
	}
	
	@PostMapping ("/create")
	public Boolean create(@RequestBody Schedule schedule) {
		try {
			scheduleRepository.save(schedule);
			return Boolean.TRUE;
		}catch (Exception e){
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/update",method = RequestMethod.POST)
	public Boolean update (@RequestBody Schedule scheduleParam,
						@PathVariable ("id") Long idSchedule) {
		try {
			Schedule schedule= scheduleRepository.findOne(idSchedule);
			schedule.setIdCourse(scheduleParam.getIdCourse());
			schedule.setIdRoom(scheduleParam.getIdRoom());
			schedule.setStartDate(scheduleParam.getStartDate());
			schedule.setEndDate(scheduleParam.getEndDate());
			schedule.setIdMainTrainer(schedule.getIdMainTrainer());
			schedule.setIdBackupTrainer(scheduleParam.getIdBackupTrainer());
			schedule.setCapacity(scheduleParam.getCapacity());
			scheduleRepository.save(schedule);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/delete", method = RequestMethod.GET)
	public Boolean delete (@PathVariable ("id") Long idSchedule) {
		try {
			scheduleRepository.delete(idSchedule);
			return Boolean.TRUE;
		}catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
		
	private ScheduleData ConvertSchedulesToScheduleData(Schedule schedule) {
		ScheduleData scheduleData = new ScheduleData(); 
		scheduleData.setIdSchedule(schedule.getIdSchedule());
		scheduleData.setName(courseRepository.findOne(schedule.getIdCourse()).getName());
		scheduleData.setMainTrainer(userRepository.findOne(schedule.getIdMainTrainer()).getName());
		scheduleData.setBackupTrainer(userRepository.findOne(schedule.getIdBackupTrainer()).getName());
		Room room = roomRepository.findOne(schedule.getIdRoom());
		scheduleData.setRoom(room.getName() + " - " + officeRepository.findOne(room.getIdOffice()).getCity());
		scheduleData.setDay("-");
		Date startDate = schedule.getStartDate();
		scheduleData.setStartTime(new SimpleDateFormat("d MMMM yyyy HH:mm").format(startDate));
		Date endDate = schedule.getStartDate();
		scheduleData.setEndTime(new SimpleDateFormat("d MMMM yyyy HH:mm").format(endDate));
		scheduleData.setCapacity(schedule.getCapacity());
		scheduleData.setParticipantNumber(enrollmentRepository.findByIdSchedule(schedule.getIdSchedule()).size());
		return scheduleData;
	}
}
