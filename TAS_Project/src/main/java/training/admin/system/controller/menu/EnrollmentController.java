package training.admin.system.controller.menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.AddEnrollment;
import training.admin.system.EnrollmentData;
import training.admin.system.model.Enrollment;
import training.admin.system.model.Schedule;
import training.admin.system.model.User;
import training.admin.system.repository.CourseRepository;
import training.admin.system.repository.EnrollmentRepository;
import training.admin.system.repository.ScheduleRepository;
import training.admin.system.repository.TrainingRepository;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	TrainingRepository trainingRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Enrollment> findAll(){
		return enrollmentRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Enrollment> findAll(Pageable pageable){
		return enrollmentRepository.findAll(pageable);
	}
	
	@PostMapping (value="/add")
	public boolean add (@RequestBody AddEnrollment newEnrollmentData) {
		try {
			for (int i=0; i<newEnrollmentData.getIdUser().size(); i++) {
				Enrollment enrollment = new Enrollment ();
				Schedule schedule = scheduleRepository.findOne(newEnrollmentData.getIdSchedule());
				enrollment.setSchedule(schedule);
				User user = userRepository.findOne(newEnrollmentData.getIdUser().get(i));
				enrollment.setUser(user);
				enrollmentRepository.save(enrollment);
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	
	@GetMapping (value="/findByUser/{idUser}")
	public List <EnrollmentData> findByUser (@PathVariable Long idUser) {
		List <EnrollmentData> result = new ArrayList<EnrollmentData>();
		User user = userRepository.findOne(idUser);
		List <Enrollment> list = enrollmentRepository.findByUser(user);
		for (Enrollment enrollment:list) {
			result.add(convertEnrollmentToEnrollmentData(enrollment));
		}
		return result;
	}
	
	@GetMapping (value="/findBySchedule/{idSchedule}")
	public List <EnrollmentData> findBySchedule (@PathVariable Long idSchedule) {
		List <EnrollmentData> result = new ArrayList<EnrollmentData>();
		Schedule schedule = scheduleRepository.findOne(idSchedule);
		List <Enrollment> list = enrollmentRepository.findBySchedule(schedule);
		for (Enrollment enrollment:list) {
			result.add(convertEnrollmentToEnrollmentData(enrollment));
		}
		return result;
	}
	
	@DeleteMapping (value="/{idEnrollment}/delete")
	public Boolean delete (@PathVariable Long idEnrollment) {
		try {
			enrollmentRepository.delete(idEnrollment);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public EnrollmentData convertEnrollmentToEnrollmentData(Enrollment enrollment) {
		EnrollmentData enrollmentData = new EnrollmentData();
		Schedule schedule = enrollment.getSchedule();
		String trainingName = schedule.getTraining().getTrainingName();
		String courseName = schedule.getCourse().getName();
		String trainerName = schedule.getMainTrainer().getName();
		Date startTime = schedule.getStartDate();
		Date endTime = schedule.getEndDate();
		String status;
		
		Date today = new Date(System.currentTimeMillis());
		if (today.compareTo(startTime)<0) {
			status = "Not Started";
		} else if (today.compareTo(endTime)<=0) {
			status = "In Progress";
		} else {
			status = "Done";
		}
		enrollmentData.setIdEnrollment(enrollment.getIdEnrollment());
		enrollmentData.setUserNumber(enrollment.getUser().getIdUser());
		enrollmentData.setUserName(enrollment.getUser().getName());
		enrollmentData.setPeriodName(trainingName);
		enrollmentData.setCourseName(courseName);
		enrollmentData.setStartTime(new SimpleDateFormat("d MMMM yyyy").format(startTime));
		enrollmentData.setEndTime(new SimpleDateFormat("d MMMM yyyy").format(endTime));
		enrollmentData.setTrainer(trainerName);
		enrollmentData.setStatus(status);
		return enrollmentData;
	}
}

