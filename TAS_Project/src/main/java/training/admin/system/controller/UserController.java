package training.admin.system.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import training.admin.system.model.User;
import training.admin.system.repository.UserRepository;
import training.admin.system.security.MD5Hash;

@RestController
@RequestMapping ("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping (value="", method=RequestMethod.GET)
	public ModelAndView root(){
		return new ModelAndView("redirect:/user/all"); 
	}
	
	@RequestMapping (value="/all", method=RequestMethod.GET)
	public Iterable<User> findAll(){
		return userRepository.findAll();
	}
	
	@RequestMapping (value="/allPage", method=RequestMethod.GET)
	public Page<User> findAll(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	@RequestMapping (value="/create", method=RequestMethod.POST)
	public String add(@RequestBody User user) {
		String password = user.getPassword();
		MD5Hash md5Hash = new MD5Hash(password);
		try {
			user.setPassword(md5Hash.getHexString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		userRepository.save(user);
		return "Test";
	}
	
	@RequestMapping (value="/update",method = RequestMethod.POST)
	public void update (@RequestBody User userParam) {
		User user = userRepository.findOne(userParam.getIdUser());
		user.setName(userParam.getName());
		user.setEmail(userParam.getEmail());
		user.setGrade(userParam.getGrade());
		user.setJobFamilyStream(userParam.getJobFamilyStream());
		user.setOffice(userParam.getOffice());
		user.setUsername(userParam.getUsername());
		user.setPassword(userParam.getPassword());
		userRepository.save(user);
	}
	
	@RequestMapping (value="/delete", method = RequestMethod.GET)
	public void delete (@RequestParam ("id") Long idUser) {
		userRepository.delete(idUser);
	}
	
}
