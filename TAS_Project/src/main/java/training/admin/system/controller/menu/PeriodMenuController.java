package training.admin.system.controller.menu;

import java.text.SimpleDateFormat;
import java.time.Period;
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
import training.admin.system.UserData;
import training.admin.system.model.Training;
import training.admin.system.model.User;
import training.admin.system.model.UserRole;
import training.admin.system.repository.CourseRepository;
import training.admin.system.repository.OfficeRepository;
import training.admin.system.repository.RoomRepository;
import training.admin.system.repository.ScheduleRepository;
import training.admin.system.repository.TrainingRepository;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping ("/period")
public class PeriodMenuController {
	
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
	
	@GetMapping("/all")
	public List <PeriodData> findAll() {
		List<PeriodData> periodsData = new ArrayList<PeriodData>(); 
		List<Training> listTraining = trainingRepository.findAll();
		for (Training training:listTraining) {
			periodsData.add(convertTrainingToPeriodData(training));
		}
		return periodsData;
	}
	
	@GetMapping ("/{id}")
	public Object findOne(@PathVariable ("id") Long idTraining) {
		try {
			return convertTrainingToPeriodData(trainingRepository.findOne(idTraining));
		}catch (Exception e) {
			System.out.println(e);
			return false;
		} 
	}
	
	@PostMapping ("/create")
	public Boolean create(@RequestBody Training training) {
		Date currentDate = new Date(System.currentTimeMillis());
		try {
			training.setCreatedDate(currentDate);
			training.setUpdatedDate(currentDate);
			trainingRepository.save(training);
			return Boolean.TRUE;
		}catch (Exception e){
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/update",method = RequestMethod.POST)
	public Boolean update (@RequestBody Training trainingParam,
						@PathVariable ("id") Long idTraining) {
		Date currentDate = new Date(System.currentTimeMillis());
		try {
			Training training = trainingRepository.findOne(idTraining);
			training.setTrainingName(trainingParam.getTrainingName());
			training.setStartDate(trainingParam.getStartDate());
			training.setEndDate(trainingParam.getEndDate());
			training.setActive(trainingParam.isActive());
			training.setOpenEnrollment(trainingParam.isOpenEnrollment());
			training.setCreatedBy(trainingParam.getCreatedBy());
			training.setCreatedDate(currentDate);
			training.setUpdatedBy(trainingParam.getUpdatedBy());
			training.setUpdatedDate(currentDate);
			trainingRepository.save(training);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/delete", method = RequestMethod.DELETE)
	public Boolean delete (@PathVariable ("id") Long idTraining) {
		try {
			trainingRepository.delete(idTraining);
			return Boolean.TRUE;
		}catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
	}

	private PeriodData convertTrainingToPeriodData(Training training) {
		PeriodData periodData = new PeriodData();
		periodData.setIdTraining(training.getIdTraining());
		periodData.setName(training.getTrainingName());
		String active = training.isActive() ? "Yes" : "No";
		periodData.setActive(active);
		periodData.setCourses(scheduleRepository.findByIdTraining(training.getIdTraining()).size());
		Date startDate = training.getStartDate();
		periodData.setStartDate(new SimpleDateFormat("d MMMM yyyy").format(startDate));
		Date endDate = training.getEndDate();
		periodData.setEndDate(new SimpleDateFormat("d MMMM yyyy").format(endDate));
		periodData.setCreatedBy(userRepository.findOne(training.getCreatedBy()).getName());
		periodData.setUpdatedBy(userRepository.findOne(training.getUpdatedBy()).getName());
		periodData.setBccTraining(training.getBccTraining());
		periodData.setOpenEnrollment(training.isOpenEnrollment());
		return periodData;
	}
	
}
