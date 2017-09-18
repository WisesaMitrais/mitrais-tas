package training.admin.system.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.LoginData;
import training.admin.system.model.Role;
import training.admin.system.model.User;
import training.admin.system.model.UserRole;
import training.admin.system.repository.RoleRepository;
import training.admin.system.repository.UserRepository;
import training.admin.system.repository.UserRoleRepository;
import training.admin.system.security.MD5Hash;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public List<LoginData> login(@RequestParam ("username") String username, @RequestParam ("password") String password) {
		List<LoginData> res = new ArrayList<LoginData>();
		LoginData loginData = new LoginData();
		
		MD5Hash md5Hash = new MD5Hash(password);
		try {
			password = md5Hash.getHexString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.add(new LoginData());
		
		User user;
		if ((user = userRepository.findByUsernameAndPassword(username, password))!=null) {
			loginData.setIdUser(user.getIdUser());
			loginData.setName(user.getName());
			
			List <UserRole> userRoles = new ArrayList<UserRole>();
			userRoles = userRoleRepository.findByIdUser(user.getIdUser());

			List <Role> roles = new ArrayList<Role>();
			for (UserRole userRole:userRoles) {
				roles.add(roleRepository.findOne(userRole.getIdRole()));
			}
			
			loginData.setRoles(roles);
			res.remove(0);
			res.add(loginData);
		}
		return res;
	}
	
	@RequestMapping (value="/getrole", method=RequestMethod.GET)
	public List<UserRole> getRole(@RequestParam ("idUser") Long idUser) {
		System.out.println("id_user = " + idUser);
		System.out.println(userRoleRepository.findByIdUserRole(idUser).toString());
//		return userRoleRepository.findByIdUserRole(idUser);
//		return userRoleRepository.findByIdRole(idUser);
 		return userRoleRepository.findByIdUser(idUser);
	}
	
	@RequestMapping (value="/password", method=RequestMethod.GET)
	public String test(@RequestParam ("password") String password) {
		MD5Hash md5Hash = new MD5Hash(password);
		try {
			password = md5Hash.getHexString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
}
