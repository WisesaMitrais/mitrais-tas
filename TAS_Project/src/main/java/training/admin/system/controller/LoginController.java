package training.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.User;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/")
	public User login() {
		return null;
	}
}
