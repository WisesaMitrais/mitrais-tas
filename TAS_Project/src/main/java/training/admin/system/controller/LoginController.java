package training.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.User;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public User login(@RequestParam ("username") String username, @RequestParam ("password") String password) {
		User user;
		if ((user = userRepository.findByUsernameAndPassword(username, password))!=null) {
			return user;
		}
		return null;
	}
}
