package training.admin.system.controller.menu;

import java.util.ArrayList;
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

import training.admin.system.AssessmentData;
import training.admin.system.EditAssessment;
import training.admin.system.model.Assessment;
import training.admin.system.model.Enrollment;
import training.admin.system.model.Schedule;
import training.admin.system.repository.AssesmentRepository;
import training.admin.system.repository.EnrollmentRepository;
import training.admin.system.repository.ScheduleRepository;

@RestController
@RequestMapping ("/assessment")
public class AssessmentController {
	
	@Autowired
	private AssesmentRepository assesmentRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@RequestMapping (value="/all", method=RequestMethod.GET)
	public List<Assessment> findAll(){
		return assesmentRepository.findAll();
	}
	
	@RequestMapping (value="/allPage", method=RequestMethod.GET)
	public Page<Assessment> findAll(Pageable pageable){
		return assesmentRepository.findAll(pageable);
	}
	
	@RequestMapping (value="/create", method=RequestMethod.POST)
	public String add(@RequestBody Assessment assesment) {
		assesmentRepository.save(assesment);
		return "Test";
	}
	
	@GetMapping (value="/findBySchedule/{idSchedule}")
	public List<AssessmentData> findBySchedule (@PathVariable Long idSchedule){
		List <AssessmentData> assessmentDatas = new ArrayList<AssessmentData>();
		
		Schedule schedule = scheduleRepository.findOne(idSchedule);
		List <Enrollment> enrollments = enrollmentRepository.findBySchedule(schedule);
		Integer i =1;
		for (Enrollment enrollment:enrollments) {
			AssessmentData assessmentData = createAssessmentData(enrollment);
			assessmentData.setNumber(i);
			assessmentDatas.add(assessmentData);
			i++;
		}
		
		return assessmentDatas;
	}
	
	@PostMapping (value = "/findBySchedule/{idSchedule}/update")
	public Boolean update (@PathVariable Long idSchedule, @RequestBody List<EditAssessment> editAssessments) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("POST = " + objectMapper.writeValueAsString(editAssessments));
		
		try {
			Integer index = 0;
			for (EditAssessment editAssessment:editAssessments) {
				Enrollment enrollment = enrollmentRepository.findOne(editAssessment.getIdEnrollment());
				Assessment assessment = assesmentRepository.findByEnrollment(enrollment);
				if(assessment==null) {
					assessment = new Assessment();
					assessment.setEnrollment(enrollment);
				} 
				assessment.setStatus(editAssessments.get(index).getStatus());
				assesmentRepository.save(assessment);
				index++;
			}
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
		
	}
	
	public AssessmentData createAssessmentData(Enrollment enrollment) {
		AssessmentData assessmentData = new AssessmentData();
		assessmentData.setIdUser(enrollment.getUser().getIdUser());
		assessmentData.setIdEnrollment(enrollment.getIdEnrollment());
		assessmentData.setName(enrollment.getUser().getName());
		
		String status = "-";
		Assessment assessment = assesmentRepository.findByEnrollment(enrollment);
		if (assessment != null)
			status = assessment.getStatus();
		
		assessmentData.setStatus(status);
		
		return assessmentData;
	}
}
