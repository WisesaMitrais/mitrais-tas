package training.admin.system.controller.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.repository.CourseRepository;
import training.admin.system.repository.OfficeRepository;
import training.admin.system.repository.RoomRepository;
import training.admin.system.repository.ScheduleRepository;
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
	

	
}
