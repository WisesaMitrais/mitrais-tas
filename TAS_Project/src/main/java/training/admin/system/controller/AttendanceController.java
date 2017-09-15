package training.admin.system.controller;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Attendance;
import training.admin.system.repository.AttendanceRepository;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List <Attendance> findAll(){
		return attendanceRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page <Attendance> findAll(Pageable pageable){
		return attendanceRepository.findAll(pageable);
	}
}
