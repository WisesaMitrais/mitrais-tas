package training.admin.system.controller.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import training.admin.system.AchievementData;
import training.admin.system.EditAchievement;
import training.admin.system.model.Achievement;
import training.admin.system.model.Course;
import training.admin.system.model.Training;
import training.admin.system.model.User;
import training.admin.system.repository.AchievementRepository;
import training.admin.system.repository.CourseRepository;
import training.admin.system.repository.OfficeRepository;
import training.admin.system.repository.TrainingRepository;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping("/achievement")
public class AchievementController {
	
	@Autowired
	AchievementRepository achievementRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OfficeRepository officeRepository;
	@Autowired
	TrainingRepository trainingRepository;
	@Autowired
	CourseRepository courseRepository;

	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Achievement> findAll( ) {
		return achievementRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Achievement> findAll(Pageable pageable) {
		return achievementRepository.findAll(pageable);
	}
	
	@GetMapping (value="/findAllUser")
	public List<AchievementData> findAllUser(){
		List <AchievementData> listAchievment = new ArrayList<AchievementData>();
		List <User> users = userRepository.findAll();
		for (User user:users) {
			listAchievment.add(convertAchievementToAchievementData(user));
		}
		return listAchievment;
	}
	
	@GetMapping(value="/findByUser/{idUser}")
	public AchievementData findByUser(@PathVariable Long idUser){
		User user = userRepository.findOne(idUser);
		return convertAchievementToAchievementData(user);
	}
	
	@PostMapping(value="/findByUser/{idUser}/update")
	public Boolean updateAchievement(@PathVariable Long idUser,
									@RequestBody EditAchievement editAchievement) throws JsonProcessingException {
		
//		ObjectMapper objectMapper = new ObjectMapper();
//		System.out.println("Update Achievement JSON = " + objectMapper.writeValueAsString(editAchievement));
//		
		try {
			User user = userRepository.findOne(idUser);
			System.out.println(user.getName());
			List<String> bccCourses = getBccCourse();
			for (String bccCourse:bccCourses) {
				String status = "";
//				System.out.println("======================\nCourse = " + bccCourse);
				Training training = new Training();
				switch (bccCourse) {
				case "Beginning":
					status = editAchievement.getBeginningStatus();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getBeginning());
					}
					break;
				case "Low Intermediete 1":
					status =editAchievement.getLI1Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getLI1());
					}
					break;
				case "Low Intermediete 2":
					status = editAchievement.getLI2Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getLI2());
					}
					break;
				case "Intermediete 1":
					status = editAchievement.getInt1Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getInt1());
					}
					break;
				case "Intermediete 2":
					status = editAchievement.getInt2Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getInt2());
					}
					break;
				case "Business Writing 1":
					status = editAchievement.getBW1Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getBW1());
					}
					break;
				case "Business Writing 2":
					status = editAchievement.getBW2Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getBW2());
					}
					break;
				case "Communicating Effectively 1":
					status = editAchievement.getCE1Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getCE1());
					}
					break;
				case "Communicating Effectively 2":
					status = editAchievement.getCE2Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getCE2());
					}
					break;
				case "Presentation Skills 2":
					status = editAchievement.getPresentationSkill2Status();
					if(status.compareTo("Term")==0) {
						training = trainingRepository.findOne(editAchievement.getPresentationSkill2());
					}
					break;
					
				default:
					break;
				}

//				System.out.println("Status = " + status);
//				System.out.println("Training = " + training.getTrainingName());
				Course course = courseRepository.findByName(bccCourse);
				List<Achievement> achievements = achievementRepository.findByUserAndCourse(user, course);
				
//				System.out.println(objectMapper.writeValueAsString(achievements));
				
				if (achievements.size()==0) {
					Achievement newAchievement = new Achievement();
					newAchievement.setUser(user);
					newAchievement.setCourse(course);
					newAchievement.setStatus(status);
					if (status.compareTo("Term")==0)
						newAchievement.setTraining(training);
					else
						newAchievement.setTraining(null);
					achievementRepository.save(newAchievement);
				} else {
					Achievement achievement = achievements.get(0);
					achievement.setStatus(status);
					if (status.compareTo("Term")==0)
						achievement.setTraining(training);
					else
						achievement.setTraining(null);
					achievementRepository.save(achievement);
				}
			}
				
			return Boolean.TRUE;
		} catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
		
	}
	
	
	@GetMapping (value="/download")
	@ResponseBody
	public Boolean exportToExcel(HttpServletResponse response) { 
		try {
			File newFile = new File("Achievement.xls");
			WritableWorkbook workbook = Workbook.createWorkbook(newFile);
            WritableSheet sheet = workbook.createSheet("Achievement", 0);
            
           	List <String> header = new ArrayList<String>();
           	header.add("Employee ID");
           	header.add("Employee Name");
           	header.add("Job Family");
           	header.add("Grade");
           	header.add("Office");
           	header.add("Beginning");
           	header.add("LI 1");
           	header.add("LI 2");
           	header.add("Int. 1");
           	header.add("Int. 2");
           	header.add("BW 1");
           	header.add("CE 1");
           	header.add("BW 2");
           	header.add("CE 2");
           	header.add("Presentatio Skills 2");
           	
        	//------------ Format font for info -------------------
           	WritableCellFormat cellInfoFormat = new WritableCellFormat();
           	WritableFont infoFont = new WritableFont(WritableFont.ARIAL, 8);
           	cellInfoFormat.setFont(infoFont);

           	sheet.mergeCells(1, 0, 15, 0);
           	Date now = new Date(System.currentTimeMillis());
           	String info = "Generated by system at " + now;
           	System.out.println(info);
           	Label infolabel = new Label (1, 0, info, cellInfoFormat);
           	sheet.addCell(infolabel);
           	

           	//------------ Format font for title -------------------
           	WritableCellFormat cellTitleFormat = new WritableCellFormat();
           	WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 16);
           	cellTitleFormat.setFont(titleFont);
           	cellTitleFormat.setAlignment(Alignment.CENTRE);
           	
        	sheet.mergeCells(1, 2, 15, 2);
           	Label title = new Label (1, 2, "List of Achievement - Training Admin System", cellTitleFormat);
           	sheet.addCell(title);

         
           	//------------ Format font for header--------------------
           	Colour bckcolor = (Colour) Colour.AQUA;
       		WritableCellFormat cellFormatHeader = new WritableCellFormat();
            cellFormatHeader.setBackground(bckcolor);
           	
            WritableFont font = new WritableFont(WritableFont.ARIAL);
            font.setBoldStyle(WritableFont.BOLD);
            cellFormatHeader.setFont(font);
            cellFormatHeader.setBorder(Border.ALL, BorderLineStyle.THIN);
            
           	for (int i = 1; i <= header.size(); i++) {
                Label headerLabel = new Label(i, 4, header.get(i-1), cellFormatHeader);
                sheet.addCell(headerLabel);
            }
           	
           	List <AchievementData> listAchievment = new ArrayList<AchievementData>();
    		List <User> users = userRepository.findAll();
    		for (User user:users) {
    			listAchievment.add(convertAchievementToAchievementData(user));
    		}
           	
    		
    		//-------------- Format font for content -----------------------
    		WritableCellFormat cellFormatBody = new WritableCellFormat();
    		cellFormatBody.setBorder(Border.ALL, BorderLineStyle.THIN);
    		Integer row=5;
    		for (AchievementData achievementData:listAchievment) {
                sheet.addCell(new Label(1, row, achievementData.getIdEmployee().toString()));
                sheet.getWritableCell(1, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(2, row, achievementData.getEmployeeName()));
                sheet.getWritableCell(2, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(3, row, achievementData.getJobFamily()));
                sheet.getWritableCell(3, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(4, row, achievementData.getGrade()));
                sheet.getWritableCell(4, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(5, row, achievementData.getOffice()));
                sheet.getWritableCell(5, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(6, row, achievementData.getBeginning()));
                sheet.getWritableCell(6, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(7, row, achievementData.getLI1()));
                sheet.getWritableCell(7, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(8, row, achievementData.getLI2()));
                sheet.getWritableCell(8, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(9, row, achievementData.getInt1()));
                sheet.getWritableCell(9, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(10, row, achievementData.getInt2()));
                sheet.getWritableCell(10, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(11, row, achievementData.getBW1()));
                sheet.getWritableCell(11, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(12, row, achievementData.getCE1()));
                sheet.getWritableCell(12, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(13, row, achievementData.getBW2()));
                sheet.getWritableCell(13, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(14, row, achievementData.getCE2()));
                sheet.getWritableCell(14, row).setCellFormat(cellFormatBody);
                sheet.addCell(new Label(15, row, achievementData.getPresentationSkill2()));
                sheet.getWritableCell(15, row).setCellFormat(cellFormatBody);
                row++;
    		}
           	
           	workbook.write();
            workbook.close();
            

           	response.setHeader("Content-disposition","attachment; filename=" + "Achievement.xls");
            FileInputStream in = new FileInputStream(newFile);
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
            return true;
		} catch (IOException | WriteException e) {
			System.out.println(e);
		    return false;
		}
		
	}
	
	public AchievementData convertAchievementToAchievementData(User user){
		AchievementData achievementData = new AchievementData();
		achievementData.setIdEmployee(user.getIdUser());
		achievementData.setEmployeeName(user.getName());
		achievementData.setJobFamily(user.getJobFamilyStream());
		achievementData.setGrade(user.getGrade());
		achievementData.setOffice(officeRepository.getOne(user.getIdOffice()).getCity());
		
		Long _beginning = new Long(0);
		Long _LI1 = new Long(0);
		Long _LI2 = new Long(0);
		Long _Int1 = new Long(0);
		Long _Int2 = new Long(0);
		Long _BW1 = new Long(0);
		Long _BW2 = new Long(0);
		Long _CE1 = new Long(0);
		Long _CE2 = new Long(0);
		Long _PresentationSkill = new Long(0);
		
		String beginning = "-";
		String LI1 = "-";
		String LI2 = "-";
		String Int1 = "-";
		String Int2 = "-";
		String BW1 = "-";
		String BW2 = "-";
		String CE1 = "-";
		String CE2 = "-";
		String PresentationSkill = "-";
		
		String beginningStatus = "";
		String LI1Status = "-";
		String LI2Status = "-";
		String Int1Status = "-";
		String Int2Status = "-";
		String BW1Status = "-";
		String BW2Status = "-";
		String CE1Status = "-";
		String CE2Status = "-";
		String PresentationSkillStatus = "-";
		
		List<Achievement> userAchievement = achievementRepository.findByUser(user);
		for (Achievement achievement:userAchievement) {
						
			switch (achievement.getCourse().getName()) {
			case "Beginning":
				beginningStatus = achievement.getStatus();
				beginning = beginningStatus.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : beginningStatus;
				_beginning = beginningStatus.compareTo("Term")==0 ? achievement.getTraining().getIdTraining() : _beginning;
				break;
			case "Low Intermediete 1":
				LI1Status = achievement.getStatus();
				LI1 = LI1Status.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : LI1Status;
				_LI1 = LI1Status.compareTo("Term")==0 ? achievement.getTraining().getIdTraining() : _LI1;
				break;
			case "Low Intermediete 2":
				LI2Status = achievement.getStatus();
				LI2 = LI2Status.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : LI2Status;
				_LI2 = LI2Status.compareTo("Term")==0 ? achievement.getTraining().getIdTraining(): _LI2;
				break;
			case "Intermediete 1":
				Int1Status = achievement.getStatus();
				Int1 = Int1Status.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : Int1Status;
				_Int1 = Int1Status.compareTo("Term")==0 ? achievement.getTraining().getIdTraining(): _Int1;
				break;
			case "Intermediete 2":
				Int2Status = achievement.getStatus();
				Int2 = Int2Status.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : Int2Status;
				_Int2 = Int2Status.compareTo("Term")==0 ? achievement.getTraining().getIdTraining() : _Int2;
				break;
			case "Business Writing 1":
				BW1Status = achievement.getStatus();
				BW1 = BW1Status.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : BW1Status;				
				_BW1 = BW1Status.compareTo("Term")==0 ? achievement.getTraining().getIdTraining(): _BW1;
				break;
			case "Business Writing 2":
				BW2Status = achievement.getStatus();
				BW2 = BW2Status.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : BW2Status;
				_BW2 = BW2Status.compareTo("Term")==0 ? achievement.getTraining().getIdTraining(): _BW2;
				break;
			case "Communicating Effectively 1":
				CE1Status = achievement.getStatus();
				CE1 = CE1Status.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : CE1Status;
				_CE1 = CE1Status.compareTo("Term")==0 ? achievement.getTraining().getIdTraining(): _CE1;
				break;
			case "Communicating Effectively 2":
				CE2Status = achievement.getStatus();
				CE2 = CE2Status.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : CE2Status;
				_CE2 = CE2Status.compareTo("Term")==0 ? achievement.getTraining().getIdTraining() : _CE2;
				break;
			case "Presentation Skills 2":
				PresentationSkillStatus = achievement.getStatus();
				PresentationSkill = PresentationSkillStatus.compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : PresentationSkillStatus;
				_PresentationSkill = PresentationSkillStatus.compareTo("Term")==0 ? achievement.getTraining().getIdTraining() : _PresentationSkill;
				break;
			default:
				break;
			}
		}
		achievementData.set_beginning(_beginning);
		achievementData.set_LI1(_LI1);
		achievementData.set_LI2(_LI2);
		achievementData.set_Int1(_Int1);
		achievementData.set_Int2(_Int2);
		achievementData.set_BW1(_BW1);
		achievementData.set_CE1(_CE1);
		achievementData.set_BW2(_BW2);
		achievementData.set_CE2(_CE2);
		achievementData.set_presentationSkill2(_PresentationSkill);
		achievementData.setBeginning(beginning);
		achievementData.setLI1(LI1);
		achievementData.setLI2(LI2);
		achievementData.setInt1(Int1);
		achievementData.setInt2(Int2);
		achievementData.setBW1(BW1);
		achievementData.setCE1(CE1);
		achievementData.setBW2(BW2);
		achievementData.setCE2(CE2);
		achievementData.setPresentationSkill2(PresentationSkill);
		achievementData.setBeginningStatus(beginningStatus);
		achievementData.setLI1Status(LI1Status);
		achievementData.setLI2Status(LI2Status);
		achievementData.setInt1Status(Int1Status);
		achievementData.setInt2Status(Int2Status);
		achievementData.setBW1Status(BW1Status);
		achievementData.setCE1Status(CE1Status);
		achievementData.setBW2Status(BW2Status);
		achievementData.setCE2Status(CE2Status);
		achievementData.setPresentationSkill2Status(PresentationSkillStatus);
		return achievementData;
	}
	
	public List <String> getBccCourse() {
		List<String> bccCourse= new ArrayList<String>();
		bccCourse.add("Beginning");
		bccCourse.add("Low Intermediete 1");
		bccCourse.add("Low Intermediete 2");
		bccCourse.add("Intermediete 1");
		bccCourse.add("Intermediete 2");
		bccCourse.add("Business Writing 1");
		bccCourse.add("Communicating Effectively 1");
		bccCourse.add("Business Writing 2");
		bccCourse.add("Communicating Effectively 2");
		bccCourse.add("Presentation Skills 2");
		
		return bccCourse;
	}
}
