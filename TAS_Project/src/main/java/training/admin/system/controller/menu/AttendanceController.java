package training.admin.system.controller.menu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.AttendanceData;
import training.admin.system.model.Attendance;
import training.admin.system.model.Enrollment;
import training.admin.system.model.Schedule;
import training.admin.system.repository.AttendanceRepository;
import training.admin.system.repository.EnrollmentRepository;
import training.admin.system.repository.ScheduleRepository;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired
	AttendanceRepository attendanceRepository;
	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List <Attendance> findAll(){
		return attendanceRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page <Attendance> findAll(Pageable pageable){
		return attendanceRepository.findAll(pageable);
	}
	
	@GetMapping(value="/findBySchedule/{idSchedule}")
	public List <AttendanceData> findBySchedule (@PathVariable Long idSchedule){
		List <AttendanceData> attendanceDatas = new ArrayList<AttendanceData>();
		
		Schedule schedule = scheduleRepository.findOne(idSchedule);
		List <Enrollment> enrollments = enrollmentRepository.findBySchedule(schedule);
		for (Enrollment enrollment:enrollments) {
			attendanceDatas.add(convertAttendanceToAttendanceData(enrollment));
		}
		
		return attendanceDatas;
	}
	
	
	private AttendanceData convertAttendanceToAttendanceData(Enrollment enrollment) {
		AttendanceData attendanceData = new AttendanceData();
		
		attendanceData.setIdSchedule(enrollment.getSchedule().getIdSchedule());
		attendanceData.setIdUser(enrollment.getUser().getIdUser());
		attendanceData.setUserName(enrollment.getUser().getName());

		List<Attendance> attendances = attendanceRepository.findByEnrollment(enrollment);			
		List <String> listStatus = new ArrayList<String>();
		for (Attendance attendance:attendances) {
			listStatus.add(attendance.getStatus());
		}
		attendanceData.setStatus(listStatus);
		
		List<Date> dates = new ArrayList<Date>();
		Schedule schedule = enrollment.getSchedule();
		Date startDate = schedule.getStartDate();
		Date endDate = schedule.getEndDate();
		if(schedule.getPeriodic()) {
			Integer day = Integer.parseInt(schedule.getDay());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			Integer selisih = (day+1) - calendar.get(Calendar.DAY_OF_WEEK);
			selisih = selisih < 0 ? 7 + selisih : selisih;
			calendar.add(Calendar.DATE, selisih);
			while (calendar.getTime().before(endDate)) {
				System.out.println(calendar.getTime());
				calendar.add(Calendar.DATE, 7);
				dates.add(calendar.getTime());
			}
		}
		attendanceData.setDates(dates);
		
		return attendanceData;
	}
}
