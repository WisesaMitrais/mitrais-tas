package training.admin.system.controller.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import training.admin.system.AttendanceData;
import training.admin.system.EditAttendance;
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
	
	@PostMapping (value="/findBySchedule/{idSchedule}/update")
	public Boolean updateAttendance (@PathVariable Long idSchedule,
									@RequestBody List<EditAttendance> editAttendances) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("Update Achievement JSON = " + objectMapper.writeValueAsString(editAttendances));
		try {
			for (EditAttendance editAttendance:editAttendances) {
				Enrollment enrollment = enrollmentRepository.findOne(editAttendance.getIdEnrollment());
				Attendance attendance = attendanceRepository.findByEnrollment(enrollment);
				String allStatus = "";
				List<String> listStatus = editAttendance.getStatus();
				System.out.println("Update Achievement JSON = " + objectMapper.writeValueAsString(listStatus));
				if(listStatus.size()!=0) {
					for (String status:listStatus) {
						System.out.println("status = " + status);
						allStatus = allStatus+ status + ";";
					}
				} else {
					allStatus="-";
				}
				
				attendance.setStatus(allStatus);
				attendanceRepository.save(attendance);
			}
			return Boolean.TRUE;
		}catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	private AttendanceData convertAttendanceToAttendanceData(Enrollment enrollment) {		
		System.out.println(enrollment.getIdEnrollment());
		AttendanceData attendanceData = new AttendanceData();
		
		attendanceData.setIdEnrollment(enrollment.getIdEnrollment());
		attendanceData.setUserName(enrollment.getUser().getName());

		Attendance attendance = attendanceRepository.findByEnrollment(enrollment);	
		List <String> listStatusFinal = new ArrayList<String>();
		if (attendance!=null) {
			if (attendance.getStatus().compareTo("-")!=0) {
				List <String> listStatus = Arrays.asList(attendance.getStatus().split(";"));
				for (String status:listStatus) {
					switch (status) {
					case "DO":
						status = "Drop Out";
						break;
					case "EA":
						status = "Execused Absence";
						break;
					case "UA":
						status = "Unexecused Absence";
						break;
					case "P":
						status = "Present";
						break;
					case "P1":
						status = "Present (1st Session)";
						break;
					case "P2":
						status = "Present (2nd Session)";
						break;
	
					default:
						break;
					}
					listStatusFinal.add(status);
				}
			}
		} else {
			attendance = new Attendance();
			attendance.setEnrollment(enrollment);
			attendance.setStatus("-");
			attendanceRepository.save(attendance);
		}
		
		attendanceData.setStatus(listStatusFinal);
		
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
			while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
				System.out.println(calendar.getTime());
				calendar.add(Calendar.DATE, 7);
				dates.add(calendar.getTime());
			}
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
				System.out.println(calendar.getTime());
				dates.add(calendar.getTime());
				calendar.add(Calendar.DATE, 1);
			}
		}
		attendanceData.setDates(dates);
		
		return attendanceData;
	}
}
