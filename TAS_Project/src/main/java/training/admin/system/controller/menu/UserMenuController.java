package training.admin.system.controller.menu;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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

import training.admin.system.UserData;
import training.admin.system.model.User;
import training.admin.system.model.UserRole;
import training.admin.system.repository.RoleRepository;
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
	public UserData findOne(@PathVariable ("id") Long idUser) {
		return convertUserToUserData(userRepository.findOne(idUser));
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
	
	@RequestMapping (value="/{id}/delete", method = RequestMethod.GET)
	public Boolean delete (@PathVariable ("id") Long idUser) {
		try {
			userRepository.delete(idUser);
			return Boolean.TRUE;
		}catch (Exception e) {
			System.out.println(e);
			return Boolean.FALSE;
		}
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
