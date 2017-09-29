package training.admin.system.controller.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jxl.Workbook;
import jxl.format.BoldStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import training.admin.system.AchievementData;
import training.admin.system.model.Achievement;
import training.admin.system.model.User;
import training.admin.system.repository.AchievementRepository;
import training.admin.system.repository.OfficeRepository;
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
	
	@GetMapping (value="/download")
	public void exportToExcel(HttpServletResponse response) { 
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
           	
       		Colour bckcolor = (Colour) Colour.AQUA;
       		WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBackground(bckcolor);
           	
            WritableFont font = new WritableFont(WritableFont.ARIAL);
            font.setBoldStyle(WritableFont.BOLD);
            cellFormat.setFont(font);
           	
           	for (int i = 0; i < header.size(); i++) {
                Label label = new Label(i, 0, header.get(i));
                sheet.addCell(label);
                WritableCell cell = sheet.getWritableCell(i, 0);
                cell.setCellFormat(cellFormat);
            }
           	
           	List <AchievementData> listAchievment = new ArrayList<AchievementData>();
    		List <User> users = userRepository.findAll();
    		for (User user:users) {
    			listAchievment.add(convertAchievementToAchievementData(user));
    		}
           	
    		Integer row=1;
    		for (AchievementData achievementData:listAchievment) {
                sheet.addCell(new Label(0, row, achievementData.getIdEmployee().toString()));
                sheet.addCell(new Label(1, row, achievementData.getEmployeeName()));
                sheet.addCell(new Label(2, row, achievementData.getJobFamily()));
                sheet.addCell(new Label(3, row, achievementData.getGrade()));
                sheet.addCell(new Label(4, row, achievementData.getOffice()));
                sheet.addCell(new Label(5, row, achievementData.getBeginning()));
                sheet.addCell(new Label(6, row, achievementData.getLI1()));
                sheet.addCell(new Label(7, row, achievementData.getLI2()));
                sheet.addCell(new Label(8, row, achievementData.getInt1()));
                sheet.addCell(new Label(9, row, achievementData.getInt2()));
                sheet.addCell(new Label(10, row, achievementData.getBW1()));
                sheet.addCell(new Label(11, row, achievementData.getCE1()));
                sheet.addCell(new Label(12, row, achievementData.getBW2()));
                sheet.addCell(new Label(13, row, achievementData.getCE2()));
                sheet.addCell(new Label(14, row, achievementData.getPresentationSkill2()));
                row++;
    		}
           	
           	workbook.write();
            workbook.close();
            

           	response.setHeader("Content-disposition","attachment; filename=" + "Achievement.xls");
            FileInputStream in = new FileInputStream(newFile);
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
		} catch (IOException | WriteException e) {
		      throw new RuntimeException("IOError writing file to output stream");
		}
		
	}
	
	public AchievementData convertAchievementToAchievementData(User user){
		AchievementData achievementData = new AchievementData();
		achievementData.setIdEmployee(user.getIdUser());
		achievementData.setEmployeeName(user.getName());
		achievementData.setJobFamily(user.getJobFamilyStream());
		achievementData.setGrade(user.getGrade());
		achievementData.setOffice(user.getOffice().getCity());
		
		String beginning = " ";
		String LI1 = " ";
		String LI2 = " ";
		String Int1 = " ";
		String Int2 = " ";
		String BW1 = " ";
		String BW2 = " ";
		String CE1 = " ";
		String CE2 = " ";
		String PresentationSkill = " ";
		
		List<Achievement> userAchievement = achievementRepository.findByUser(user);
		for (Achievement achievement:userAchievement) {
						
			switch (achievement.getCourse().getName()) {
			case "Beginning":
				beginning = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Low Intermediete 1":
				LI1 = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Low Intermediete 2":
				LI2 = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Intermediete 1":
				Int1 = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Intermediete 2":
				Int2 = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Business Writing 1":
				BW1 = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Business Writing 2":
				BW2 = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Communicating Effectively 1":
				CE1 = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Communicating Effectively 2":
				CE2 = achievement.getStatus().compareTo("Term")==0 ? achievement.getTraining().getTrainingName() : achievement.getStatus();
				break;
			case "Presentation Skills 2":
				CE2 = achievement.getStatus();
				break;
			default:
				break;
			}
		}
		/*String beginning = "-";
		List<Achievement> beginningAchievement = achievementRepository.findByIdUserAndIdCourse(user.getIdUser(), achievement.getIdCourse());
		if (beginningAchievement.size()>0){
			
		}*/
		
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
		return achievementData;
	}
}
