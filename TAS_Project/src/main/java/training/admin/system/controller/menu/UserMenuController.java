package training.admin.system.controller.menu;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.validator.internal.util.privilegedactions.NewSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.AddUser;
import training.admin.system.Trainer;
import training.admin.system.UserData;
import training.admin.system.model.EligibleParticipant;
import training.admin.system.model.Office;
import training.admin.system.model.Role;
import training.admin.system.model.Training;
import training.admin.system.model.User;
import training.admin.system.model.UserRole;
import training.admin.system.repository.EligibleParticipantRepository;
import training.admin.system.repository.OfficeRepository;
import training.admin.system.repository.RoleRepository;
import training.admin.system.repository.TrainingRepository;
import training.admin.system.repository.UserRepository;
import training.admin.system.repository.UserRoleRepository;
import training.admin.system.security.MD5Hash;

@RestController
@RequestMapping("/user")
public class UserMenuController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	EligibleParticipantRepository eligibleParticipantRepository;
	
	@Autowired
	OfficeRepository officeRepository;
	
	@GetMapping("/all")
	public List <UserData> findAll() {
		List<UserData> usersData = new ArrayList<UserData>(); 
		List<User> users = userRepository.findAll();
		for (User user:users) {
			usersData.add(convertUserToUserData(user));
		}
		return usersData;
	}
	
	@GetMapping ("/{id}")
	public Object findOne(@PathVariable ("id") Long idUser) {
		try {
			return convertUserToUserData(userRepository.findOne(idUser));
		}
		catch (Exception exp){
			System.out.println(exp);
			return false;
		}
	}
	
	
	@RequestMapping (value="/create", method=RequestMethod.POST)
	public Boolean add(@RequestBody AddUser newUser) {
		System.out.println(newUser.getIdOffice());
		System.out.println(newUser.getRoles());
		System.out.println(newUser.toString());
		
		
		User user = new User();
		String password = newUser.getPassword();
		user.setName(newUser.getName());
		user.setEmail(newUser.getEmail());
		user.setUsername(newUser.getUsername());
		
		MD5Hash md5Hash = new MD5Hash(password);
		try {
			user.setPassword(md5Hash.getHexString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		user.setJobFamilyStream(newUser.getJobFamilyStream());
		user.setGrade(newUser.getGrade());
		user.setActive(newUser.getActive());
		
		Office office = officeRepository.findOne(newUser.getIdOffice());
		user.setIdOffice(office.getIdOffice());
		
		try {
			userRepository.save(user);
			
			List<String> roles = newUser.getRoles();
			Long idRole = new Long(0);
			for (int i=0; i<roles.size(); i++) {
				switch (roles.get(i)) {
				case "Admin":
					idRole = new Long(1);
					break;
				case "Manager":
					idRole = new Long(2);
					break;
				case "Trainer":
					idRole = new Long(3);
					break;
				case "Staff":
					idRole = new Long(4);
					break;
				default:
					break;
				}
				
				Role role = roleRepository.findOne(idRole);
				UserRole userRole = new UserRole();
				userRole.setIdRole(role.getIdRole());
				userRole.setIdUser(user.getIdUser());
				userRoleRepository.save(userRole);
			}
			return Boolean.TRUE;
		}catch (Exception e){
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/update",method = RequestMethod.POST)
	public Boolean update (@RequestBody AddUser newUser,
						@PathVariable ("id") Long idUser) {
		
		System.out.println(newUser.getRoles());
		
		try {
			User user = userRepository.findOne(idUser);
			user.setActive(newUser.getActive());
			
			List<String> roles = newUser.getRoles();
			
			List<UserRole> userRoles = userRoleRepository.findByIdUser(user.getIdUser());
			for (UserRole userRole:userRoles)
				userRoleRepository.delete(userRole.getIdUserRole());
			
			
			Long idRole = new Long(0);
			for (int i=0; i<roles.size(); i++) {
				switch (roles.get(i)) {
				case "Admin":
					idRole = new Long(1);
					break;
				case "Manager":
					idRole = new Long(2);
					break;
				case "Trainer":
					idRole = new Long(3);
					break;
				case "Staff":
					idRole = new Long(4);
					break;
				default:
					break;
				}
				
				
				Role role = roleRepository.findOne(idRole);
				UserRole userRole = new UserRole();
				userRole.setIdRole(role.getIdRole());
				userRole.setIdUser(user.getIdUser());
				System.out.println("Role name: " + role.getName());
				userRoleRepository.save(userRole);
			}
			userRepository.save(user);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/delete", method = RequestMethod.DELETE)
	public Boolean delete (@PathVariable ("id") Long idUser) {
		try {
			userRepository.delete(idUser);
			return Boolean.TRUE;
		}catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	@GetMapping (value="/findByTrainingNot/{idTraining}")
	public List<UserData> findByTrainingNot (@PathVariable Long idTraining){
		List<UserData> usersData = new ArrayList<UserData>(); 
		List<User> users = userRepository.findAll();
		for (User user:users) {
			Training training = trainingRepository.findOne(idTraining);
			List <EligibleParticipant> tmp = eligibleParticipantRepository.findByTrainingAndUser(training, user);
			if (tmp.size()<=0) {
				usersData.add(convertUserToUserData(user));
			}
		}
		return usersData;
	}
		
	@GetMapping (value="/findTrainer")
	public List<Trainer> findTrainer(){
		List<Trainer> trainers = new ArrayList<Trainer>();
		List<UserRole> userRoles = userRoleRepository.findByIdRole(new Long(3));
		for (UserRole userRole:userRoles) {
			Trainer trainer = new Trainer();
			trainer.setIdTrainer(userRole.getIdUser());
			trainer.setName(userRepository.findOne(userRole.getIdUser()).getName());
			trainers.add(trainer);
		}
		return trainers;
	}
	
	//============================================//

	private UserData convertUserToUserData(User user) {
		UserData userData = new UserData();
		userData.setIdUser(user.getIdUser());
		userData.setName(user.getName());
		userData.setActive(user.isActive());
		userData.setJobFamilyStream(user.getJobFamilyStream());
		userData.setEmail(user.getEmail());
		userData.setAccountName(user.getUsername());
		userData.setGrade(user.getGrade());
		
		List <UserRole> userRoles = userRoleRepository.findByIdUser(user.getIdUser());
		String roles ="";
		for (UserRole userRole:userRoles) {
			roles = roles + roleRepository.findOne(userRole.getIdRole()).getName() + ", ";
		}
		userData.setRole(roles);
		
		return userData;
	}
}
