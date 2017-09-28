package training.admin.system.controller.menu;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
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

import training.admin.system.Trainer;
import training.admin.system.UserData;
import training.admin.system.model.EligibleParticipant;
import training.admin.system.model.Office;
import training.admin.system.model.Role;
import training.admin.system.model.Training;
import training.admin.system.model.User;
import training.admin.system.model.UserRole;
import training.admin.system.repository.EligibleParticipantRepository;
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
	public Boolean add(@RequestBody User user) {
		String password = user.getPassword();
		MD5Hash md5Hash = new MD5Hash(password);
		try {
			user.setPassword(md5Hash.getHexString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		try {
			userRepository.save(user);
			return Boolean.TRUE;
		}catch (Exception e){
			System.out.println(e);
			return Boolean.FALSE;
		}
	}
	
	@RequestMapping (value="/{id}/update",method = RequestMethod.POST)
	public Boolean update (@RequestBody User userParam,
						@PathVariable ("id") Long idUser) {
		try {
			User user = userRepository.findOne(idUser);
			user.setName(userParam.getName());
			user.setEmail(userParam.getEmail());
			user.setGrade(userParam.getGrade());
			user.setJobFamilyStream(userParam.getJobFamilyStream());
			user.setOffice(userParam.getOffice());
			user.setUsername(userParam.getUsername());
			user.setPassword(userParam.getPassword());
			user.setActive(userParam.isActive());
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
	
	@GetMapping (value="/{idUser}/office")
	public User getOffice (@PathVariable Long idUser) {
		User user = userRepository.findOne(idUser);
		Hibernate.initialize(user.getOffice());
		return user;
	}
	
	@GetMapping (value="/findTrainer")
	public List<Trainer> findTrainer(){
		List<Trainer> trainers = new ArrayList<Trainer>();
		Role trainerRole = roleRepository.findOne(new Long(3)); 
		List<UserRole> userRoles = userRoleRepository.findByRole(trainerRole);
		for (UserRole userRole:userRoles) {
			Trainer trainer = new Trainer();
			trainer.setIdTrainer(userRole.getUser().getIdUser());
			trainer.setName(userRole.getUser().getName());
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
		
		List <UserRole> userRoles = userRoleRepository.findByUser(user);
		String roles ="";
		for (UserRole userRole:userRoles) {
			roles = roles + userRole.getRole().getName() + ", ";
		}
		userData.setRole(roles);
		
		return userData;
	}
}
